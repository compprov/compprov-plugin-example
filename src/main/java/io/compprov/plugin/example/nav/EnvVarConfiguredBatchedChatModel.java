package io.compprov.plugin.example.nav;

import dev.langchain4j.model.anthropic.AnthropicBatchChatModel;
import dev.langchain4j.model.anthropic.AnthropicChatRequestParameters;
import dev.langchain4j.model.batch.BatchPage;
import dev.langchain4j.model.batch.BatchPagination;
import dev.langchain4j.model.batch.BatchRequest;
import dev.langchain4j.model.batch.BatchResponse;
import dev.langchain4j.model.chat.BatchChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.jspecify.annotations.Nullable;

/**
 * Batch counterpart of {@link EnvVarConfiguredChatModel}, configured from the same environment variables
 * (see {@link ChatModelSettings}). Uses {@link AnthropicBatchChatModel} when {@code CHATMODEL_URL} contains
 * {@code "anthropic"}, otherwise {@link OpenAiBatchChatModel} (langchain4j-open-ai has no batch chat model).
 * <p>
 * Like the sync model, the client is built on first use, so the plugin loads without any {@code CHATMODEL_*}
 * variable set. For Claude, {@code CHATMODEL_CACHE_TTL=1h} keeps the cached system message alive longer,
 * since batch requests run in no particular order and a batch can take longer than 5 minutes.
 */
public class EnvVarConfiguredBatchedChatModel implements BatchChatModel {

    private volatile BatchChatModel batchChatModel;

    private BatchChatModel delegate() {
        var result = batchChatModel;
        if (result == null) {
            synchronized (this) {
                result = batchChatModel;
                if (result == null) {
                    result = build(ChatModelSettings.fromEnv());
                    batchChatModel = result;
                }
            }
        }
        return result;
    }

    private static BatchChatModel build(ChatModelSettings settings) {
        if (settings.isAnthropic()) {
            return AnthropicBatchChatModel.builder()
                    .baseUrl(settings.url())
                    .apiKey(settings.apiKey())
                    .modelName(settings.modelName())
                    .maxTokens(settings.maxTokens()) // per request
                    .timeout(ChatModelSettings.TIMEOUT) // per HTTP request
                    .defaultRequestParameters(AnthropicChatRequestParameters.builder()
                            .modelName(settings.modelName())
                            .maxOutputTokens(settings.maxTokens())
                            .cacheSystemMessages(true)
                            .cacheTtl(settings.cacheTtl())
                            .responseFormat(ChatModelSettings.RESPONSE_FORMAT)
                            .build())
                    .build();
        }
        return new OpenAiBatchChatModel(settings.url(),
                settings.apiKey(),
                settings.modelName(),
                settings.maxTokens(),
                settings.effort(),
                ChatModelSettings.TIMEOUT);
    }

    @Override
    public BatchResponse<ChatResponse> submit(BatchRequest<ChatRequest> batchRequest) {
        return delegate().submit(batchRequest);
    }

    @Override
    public BatchResponse<ChatResponse> retrieve(String batchId) {
        return delegate().retrieve(batchId);
    }

    @Override
    public void cancel(String batchId) {
        delegate().cancel(batchId);
    }

    @Override
    public BatchPage<ChatResponse> list(@Nullable BatchPagination batchPagination) {
        return delegate().list(batchPagination);
    }
}
