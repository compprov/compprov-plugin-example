package io.compprov.plugin.example.nav;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.batch.BatchError;
import dev.langchain4j.model.batch.BatchItemResult;
import dev.langchain4j.model.batch.BatchPage;
import dev.langchain4j.model.batch.BatchPagination;
import dev.langchain4j.model.batch.BatchRequest;
import dev.langchain4j.model.batch.BatchResponse;
import dev.langchain4j.model.batch.BatchState;
import dev.langchain4j.model.chat.BatchChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.FinishReason;
import dev.langchain4j.model.output.TokenUsage;
import org.jspecify.annotations.Nullable;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ObjectNode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * {@link BatchChatModel} over the OpenAI Batch API (Files + Batches endpoints), which langchain4j-open-ai lacks.
 * Each request becomes one {@code /v1/chat/completions} line with the same parameters as the sync
 * {@link EnvVarConfiguredChatModel}; results are returned in submission order, matched by {@code custom_id}.
 */
public class OpenAiBatchChatModel implements BatchChatModel {

    private static final String CUSTOM_ID_PREFIX = "request-";
    private static final String ENDPOINT = "/v1/chat/completions";

    private final ObjectMapper mapper = JsonMapper.builder().build();
    private final HttpClient httpClient;
    private final String baseUrl;
    private final String apiKey;
    private final String modelName;
    private final int maxTokens;
    private final String reasoningEffort;
    private final Duration timeout;

    public OpenAiBatchChatModel(String baseUrl, String apiKey, String modelName, int maxTokens,
                                @Nullable String reasoningEffort, Duration timeout) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.maxTokens = maxTokens;
        this.reasoningEffort = reasoningEffort;
        this.timeout = timeout;
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
    }

    @Override
    public BatchResponse<ChatResponse> submit(BatchRequest<ChatRequest> batchRequest) {
        final var jsonl = new StringBuilder();
        final var requests = batchRequest.requests();
        for (int i = 0; i < requests.size(); i++) {
            final var line = mapper.createObjectNode();
            line.put("custom_id", CUSTOM_ID_PREFIX + i);
            line.put("method", "POST");
            line.put("url", ENDPOINT);
            line.set("body", toRequestBody(requests.get(i)));
            jsonl.append(mapper.writeValueAsString(line)).append('\n');
        }

        final var fileId = uploadBatchFile(jsonl.toString().getBytes(StandardCharsets.UTF_8)).path("id").asString();

        final var create = mapper.createObjectNode();
        create.put("input_file_id", fileId);
        create.put("endpoint", ENDPOINT);
        create.put("completion_window", "24h");
        final var batch = send(jsonRequest("batches").POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(create))));
        return BatchResponse.<ChatResponse>builder()
                .batchId(batch.path("id").asString())
                .state(toState(batch.path("status").asString()))
                .results(List.of())
                .build();
    }

    @Override
    public BatchResponse<ChatResponse> retrieve(String batchId) {
        final var batch = send(jsonRequest("batches/" + batchId).GET());
        final var state = toState(batch.path("status").asString());
        final var results = state.isTerminal() ? collectResults(batch) : List.<BatchItemResult<ChatResponse>>of();
        return BatchResponse.<ChatResponse>builder()
                .batchId(batchId)
                .state(state)
                .results(results)
                .build();
    }

    @Override
    public void cancel(String batchId) {
        send(jsonRequest("batches/" + batchId + "/cancel").POST(HttpRequest.BodyPublishers.noBody()));
    }

    @Override
    public BatchPage<ChatResponse> list(@Nullable BatchPagination batchPagination) {
        var path = "batches";
        final var query = new ArrayList<String>();
        if (batchPagination != null && batchPagination.pageSize() != null) {
            query.add("limit=" + batchPagination.pageSize());
        }
        if (batchPagination != null && batchPagination.pageToken() != null) {
            query.add("after=" + URLEncoder.encode(batchPagination.pageToken(), StandardCharsets.UTF_8));
        }
        if (!query.isEmpty()) {
            path += "?" + String.join("&", query);
        }

        final var page = send(jsonRequest(path).GET());
        final var batches = new ArrayList<BatchResponse<ChatResponse>>();
        String lastId = null;
        for (var batch : page.path("data")) {
            lastId = batch.path("id").asString();
            batches.add(BatchResponse.<ChatResponse>builder()
                    .batchId(lastId)
                    .state(toState(batch.path("status").asString()))
                    .results(List.of())
                    .build());
        }
        return new BatchPage<>(batches, page.path("has_more").asBoolean() ? lastId : null);
    }

    private ObjectNode toRequestBody(ChatRequest request) {
        final var body = mapper.createObjectNode();
        body.put("model", modelName);
        final var messages = body.putArray("messages");
        for (ChatMessage message : request.messages()) {
            final var m = messages.addObject();
            if (message instanceof SystemMessage sm) {
                m.put("role", "system").put("content", sm.text());
            } else if (message instanceof UserMessage um) {
                m.put("role", "user").put("content", um.singleText());
            } else if (message instanceof AiMessage am) {
                m.put("role", "assistant").put("content", am.text());
            } else {
                throw new IllegalArgumentException("Unsupported message type: " + message.type());
            }
        }
        body.put("max_completion_tokens", maxTokens);
        body.put("temperature", 1.0);
        if (reasoningEffort != null && !reasoningEffort.isBlank()) {
            body.put("reasoning_effort", reasoningEffort);
        }

        final var schema = mapper.createObjectNode();
        schema.put("type", "object");
        final var properties = schema.putObject("properties");
        properties.putObject("risk_score").put("type", "number");
        properties.putObject("markdown_report").put("type", "string");
        schema.putArray("required").add("risk_score").add("markdown_report");

        final var responseFormat = body.putObject("response_format");
        responseFormat.put("type", "json_schema");
        final var jsonSchema = responseFormat.putObject("json_schema");
        jsonSchema.put("name", "PromptProcessingResult");
        jsonSchema.set("schema", schema);
        return body;
    }

    private List<BatchItemResult<ChatResponse>> collectResults(JsonNode batch) {
        final var byIndex = new HashMap<Integer, BatchItemResult<ChatResponse>>();
        for (var fileField : List.of("output_file_id", "error_file_id")) {
            final var fileId = text(batch, fileField);
            if (fileId == null) {
                continue;
            }
            for (var line : downloadFile(fileId).split("\n")) {
                if (!line.isBlank()) {
                    final var node = mapper.readTree(line);
                    byIndex.put(toIndex(node.path("custom_id").asString()), toItemResult(node));
                }
            }
        }

        final var total = batch.path("request_counts").path("total").asInt();
        final var results = new ArrayList<BatchItemResult<ChatResponse>>(total);
        for (int i = 0; i < total; i++) {
            results.add(byIndex.getOrDefault(i, BatchItemResult.failure(
                    new BatchError(0, "No result returned for " + CUSTOM_ID_PREFIX + i, List.of()))));
        }
        return results;
    }

    private BatchItemResult<ChatResponse> toItemResult(JsonNode line) {
        final var response = line.path("response");
        final var statusCode = response.path("status_code").asInt();
        final var body = response.path("body");
        if (!response.isMissingNode() && !response.isNull() && statusCode == 200) {
            return BatchItemResult.success(toChatResponse(body));
        }

        final var error = line.hasNonNull("error") ? line.path("error") : body.path("error");
        final var message = text(error, "message");
        return BatchItemResult.failure(new BatchError(statusCode,
                message != null ? message : "Request failed: " + line, List.of()));
    }

    private ChatResponse toChatResponse(JsonNode completion) {
        final var choice = completion.path("choices").path(0);
        final var usage = completion.path("usage");
        final var content = text(choice.path("message"), "content");
        return ChatResponse.builder()
                .id(text(completion, "id"))
                .modelName(text(completion, "model"))
                .aiMessage(AiMessage.from(content != null ? content : ""))
                .finishReason(toFinishReason(text(choice, "finish_reason")))
                .tokenUsage(new TokenUsage(
                        usage.path("prompt_tokens").asInt(),
                        usage.path("completion_tokens").asInt(),
                        usage.path("total_tokens").asInt()))
                .build();
    }

    private JsonNode uploadBatchFile(byte[] content) {
        final var boundary = "----compprov-" + UUID.randomUUID();
        final var out = new ByteArrayOutputStream();
        final var head = "--" + boundary + "\r\n"
                + "Content-Disposition: form-data; name=\"purpose\"\r\n\r\n"
                + "batch\r\n"
                + "--" + boundary + "\r\n"
                + "Content-Disposition: form-data; name=\"file\"; filename=\"batch.jsonl\"\r\n"
                + "Content-Type: application/jsonl\r\n\r\n";
        out.writeBytes(head.getBytes(StandardCharsets.UTF_8));
        out.writeBytes(content);
        out.writeBytes(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return send(request("files")
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofByteArray(out.toByteArray())));
    }

    private String downloadFile(String fileId) {
        return sendRaw(request("files/" + fileId + "/content").GET());
    }

    private HttpRequest.Builder request(String path) {
        return HttpRequest.newBuilder(URI.create(baseUrl + path))
                .timeout(timeout)
                .header("Authorization", "Bearer " + apiKey);
    }

    private HttpRequest.Builder jsonRequest(String path) {
        return request(path).header("Content-Type", "application/json");
    }

    private JsonNode send(HttpRequest.Builder builder) {
        return mapper.readTree(sendRaw(builder));
    }

    private String sendRaw(HttpRequest.Builder builder) {
        final var request = builder.build();
        try {
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            if (response.statusCode() / 100 != 2) {
                throw new IllegalStateException("OpenAI " + request.method() + " " + request.uri()
                        + " failed with " + response.statusCode() + ": " + response.body());
            }
            return response.body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted: " + request.uri(), e);
        } catch (IOException e) {
            throw new IllegalStateException("OpenAI request failed: " + request.uri(), e);
        }
    }

    private static int toIndex(String customId) {
        return Integer.parseInt(customId.substring(CUSTOM_ID_PREFIX.length()));
    }

    private static @Nullable String text(JsonNode node, String field) {
        final var value = node.get(field);
        return (value == null || value.isNull()) ? null : value.asString();
    }

    private static BatchState toState(String status) {
        return switch (status) {
            case "validating" -> BatchState.PENDING;
            case "in_progress", "finalizing", "cancelling" -> BatchState.RUNNING;
            case "completed" -> BatchState.SUCCEEDED;
            case "failed" -> BatchState.FAILED;
            case "expired" -> BatchState.EXPIRED;
            case "cancelled" -> BatchState.CANCELLED;
            default -> BatchState.UNSPECIFIED;
        };
    }

    private static FinishReason toFinishReason(@Nullable String reason) {
        if (reason == null) {
            return null;
        }
        return switch (reason) {
            case "stop" -> FinishReason.STOP;
            case "length" -> FinishReason.LENGTH;
            case "content_filter" -> FinishReason.CONTENT_FILTER;
            case "tool_calls", "function_call" -> FinishReason.TOOL_EXECUTION;
            default -> FinishReason.OTHER;
        };
    }
}
