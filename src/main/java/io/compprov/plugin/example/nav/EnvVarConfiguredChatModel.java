package io.compprov.plugin.example.nav;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

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
        chatModel = OpenAiChatModel.builder()
                .baseUrl(System.getenv("CHATMODEL_URL"))
                .apiKey(System.getenv("CHATMODEL_API_KEY"))
                .modelName(System.getenv("CHATMODEL_NAME"))
                .responseFormat("json_schema")
                .strictJsonSchema(true)
                .build();
    }

    @Override
    public String chat(String userMessage) {
        return chatModel.chat(userMessage);
    }
}
