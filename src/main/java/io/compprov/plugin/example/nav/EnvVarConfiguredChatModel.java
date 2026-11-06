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
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.List;
import java.util.Set;

/**
 * A langchain4j {@link ChatModel} configured from environment variables, backed by one of two clients
 * depending on {@code CHATMODEL_URL}:
 * <ul>
 *   <li>If it contains {@code "anthropic"}: a native {@link AnthropicChatModel} against the Claude Messages API,
 *       with system-message caching.</li>
 *   <li>Otherwise: an {@link OpenAiChatModel} against an OpenAI-compatible chat completions endpoint, with
 *       {@code CHATMODEL_EFFORT} passed as {@code reasoning_effort}.</li>
 * </ul>
 * Both request JSON-schema structured output ({@code risk_score}/{@code markdown_report}) and use a 5-minute
 * request timeout. The variables are listed in {@link ChatModelSettings}.
 * <p>
 * The client is built on first use, so the plugin loads without any {@code CHATMODEL_*} variable set
 * (e.g. to only generate markdown prompts); the first call then fails if {@code CHATMODEL_URL} is missing.
 * <p>
 * Anthropic's OpenAI-compatibility endpoint is deliberately not used for Claude: it ignores
 * {@code response_format} and prompt caching. See
 * <a href="https://platform.claude.com/docs/en/api/openai-sdk">OpenAI SDK compatibility</a>.
 */
public class EnvVarConfiguredChatModel implements ChatModel {

    private volatile ChatModel chatModel;

    private ChatModel delegate() {
        var result = chatModel;
        if (result == null) {
            synchronized (this) {
                result = chatModel;
                if (result == null) {
                    result = build(ChatModelSettings.fromEnv());
                    chatModel = result;
                }
            }
        }
        return result;
    }

    private static ChatModel build(ChatModelSettings settings) {
        if (settings.isAnthropic()) {
            return AnthropicChatModel.builder()
                    .baseUrl(settings.url())
                    .apiKey(settings.apiKey())
                    .modelName(settings.modelName())
                    .maxTokens(settings.maxTokens())
                    .timeout(ChatModelSettings.TIMEOUT)
                    .cacheSystemMessages(true)
                    .responseFormat(ChatModelSettings.RESPONSE_FORMAT)
                    .build();
        }
        return OpenAiChatModel.builder()
                .baseUrl(settings.url())
                .apiKey(settings.apiKey())
                .modelName(settings.modelName())
                .maxCompletionTokens(settings.maxTokens())
                .timeout(ChatModelSettings.TIMEOUT)
                .temperature(1.0) // required by OpenAI reasoning models
                .reasoningEffort(settings.effort())
                // prompt caching is automatic on OpenAI
                .responseFormat(ChatModelSettings.RESPONSE_FORMAT)
                .build();
    }

    @Override
    public ChatResponse doChat(ChatRequest chatRequest) {
        return delegate().doChat(chatRequest);
    }

    @Override
    public ChatResponse chat(ChatMessage... messages) {
        return delegate().chat(messages);
    }

    @Override
    public ChatResponse chat(List<ChatMessage> messages) {
        return delegate().chat(messages);
    }

    @Override
    public ChatResponse chat(ChatRequest chatRequest) {
        return delegate().chat(chatRequest);
    }

    @Override
    public ChatResponse chat(ChatRequest chatRequest, ChatRequestOptions options) {
        return delegate().chat(chatRequest, options);
    }

    @Override
    public List<ChatModelListener> listeners() {
        return delegate().listeners();
    }

    @Override
    public ChatRequestParameters defaultRequestParameters() {
        return delegate().defaultRequestParameters();
    }

    @Override
    public ModelProvider provider() {
        return delegate().provider();
    }

    @Override
    public String chat(String userMessage) {
        return delegate().chat(userMessage);
    }

    @Override
    public Set<Capability> supportedCapabilities() {
        return delegate().supportedCapabilities();
    }
}
