package io.compprov.plugin.example.nav;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.ModelProvider;
import dev.langchain4j.model.anthropic.AnthropicChatModel;
import dev.langchain4j.model.chat.Capability;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.ChatRequestOptions;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.request.ChatRequestParameters;
import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * A langchain4j {@link ChatModel} backed by env vars config, accessed through its
 * OpenAI-compatible endpoint via {@link OpenAiChatModel}.
 * <p>
 * Discovered by {@code compprov-analytics} through {@link java.util.ServiceLoader} — this class
 * is registered as the provider for {@code dev.langchain4j.model.chat.ChatModel} in
 * {@code META-INF/services}, so packaging this plugin jar and passing it via
 * {@code --plugin=<path-to-jar>} is enough for the CLI to pick it up as the chat model used for
 * prompt processing.
 * <p>
 * Configuration is read entirely from three environment variables:
 * <ul>
 *   <li>{@code CHATMODEL_URL} — base URL of an OpenAI-compatible chat completions endpoint</li>
 *   <li>{@code CHATMODEL_API_KEY} — API key sent as the bearer token</li>
 *   <li>{@code CHATMODEL_NAME} — model name passed to the endpoint</li>
 * </ul>
 * Because Anthropic exposes an OpenAI-compatible endpoint for the Claude API, these same three
 * variables can point this model at Claude instead of an OpenAI (or OpenAI-compatible) deployment:
 * <ul>
 *   <li>{@code CHATMODEL_URL=https://api.anthropic.com/v1/}</li>
 *   <li>{@code CHATMODEL_API_KEY=<your Claude API key>}</li>
 *   <li>{@code CHATMODEL_NAME=claude-opus-4-8} (or another Claude model ID, e.g.
 *       {@code claude-sonnet-5} or {@code claude-haiku-4-5})</li>
 * </ul>
 * The compatibility layer is intended for evaluating model capabilities rather than as a
 * production integration path — it silently ignores several OpenAI-only request fields (e.g.
 * {@code strict}, {@code logprobs}, prompt caching) and does not support extended thinking output.
 * See <a href="https://platform.claude.com/docs/en/api/openai-sdk">OpenAI SDK compatibility</a>
 * for the full list of supported and ignored fields.
 */
public class EnvVarConfiguredChatModel implements ChatModel {

    private ChatModel chatModel;

    /**
     * Builds the underlying {@link OpenAiChatModel}, pointed by environment variables
     * ({@code CHATMODEL_URL}, {@code CHATMODEL_API_KEY}, {@code CHATMODEL_NAME}).
     */
    public EnvVarConfiguredChatModel() {
        final var url = System.getenv("CHATMODEL_URL");
        if (url == null) {
            throw new RuntimeException("Env var CHATMODEL_URL is not set");
        }
        if (url.toLowerCase().contains("anthropic")) {
            chatModel = new AnthropicChatModel.AnthropicChatModelBuilder()
                    .baseUrl(System.getenv("CHATMODEL_URL"))
                    .apiKey(System.getenv("CHATMODEL_API_KEY"))
                    .modelName(System.getenv("CHATMODEL_NAME"))
                    .maxTokens(100_000)
                    .timeout(Duration.ofMinutes(5))
                    .cacheSystemMessages(true)
                    .responseFormat(ResponseFormat.builder()
                            .type(ResponseFormatType.JSON)
                            .jsonSchema(JsonSchema.builder()
                                    .name("PromptProcessingResult")
                                    .rootElement(JsonObjectSchema.builder()
                                            .addStringProperty("verdict")
                                            .addNumberProperty("confidence_score")
                                            .addStringProperty("markdown_report")
                                            .required("verdict", "confidence_score", "markdown_report")
                                            .build())
                                    .build())
                            .build())
                    .build();
        } else {
            chatModel = OpenAiChatModel.builder()
                    .baseUrl(url)
                    .apiKey(System.getenv("CHATMODEL_API_KEY"))
                    .modelName(System.getenv("CHATMODEL_NAME"))
                    .responseFormat("json_object")
                    .maxTokens(100_000)
                    .timeout(Duration.ofMinutes(5))
                    .strictJsonSchema(true)
                    .build();
        }
    }

    @Override
    public ChatResponse doChat(ChatRequest chatRequest) {
        return chatModel.doChat(chatRequest);
    }

    @Override
    public ChatResponse chat(ChatMessage... messages) {
        return chatModel.chat(messages);
    }

    @Override
    public ChatResponse chat(List<ChatMessage> messages) {
        return chatModel.chat(messages);
    }

    @Override
    public ChatResponse chat(ChatRequest chatRequest) {
        return chatModel.chat(chatRequest);
    }

    @Override
    public ChatResponse chat(ChatRequest chatRequest, ChatRequestOptions options) {
        return chatModel.chat(chatRequest, options);
    }

    @Override
    public List<ChatModelListener> listeners() {
        return chatModel.listeners();
    }

    @Override
    public ChatRequestParameters defaultRequestParameters() {
        return chatModel.defaultRequestParameters();
    }

    @Override
    public ModelProvider provider() {
        return chatModel.provider();
    }

    @Override
    public String chat(String userMessage) {
        return chatModel.chat(userMessage);
    }

    @Override
    public Set<Capability> supportedCapabilities() {
        return chatModel.supportedCapabilities();
    }
}
