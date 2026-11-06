package io.compprov.plugin.example.nav;

import dev.langchain4j.model.chat.request.ResponseFormat;
import dev.langchain4j.model.chat.request.ResponseFormatType;
import dev.langchain4j.model.chat.request.json.JsonObjectSchema;
import dev.langchain4j.model.chat.request.json.JsonSchema;

import java.time.Duration;
import java.util.Locale;

/**
 * Chat model settings shared by {@link EnvVarConfiguredChatModel} and {@link EnvVarConfiguredBatchedChatModel},
 * read from environment variables:
 * <ul>
 *   <li>{@code CHATMODEL_URL} (required): base URL; one containing {@code "anthropic"} selects the native Claude client</li>
 *   <li>{@code CHATMODEL_API_KEY}: API key</li>
 *   <li>{@code CHATMODEL_NAME}: model name</li>
 *   <li>{@code CHATMODEL_MAXTOKENS}: output token limit per request, default 100000</li>
 *   <li>{@code CHATMODEL_EFFORT}: OpenAI {@code reasoning_effort}, e.g. {@code medium}; ignored for Claude</li>
 *   <li>{@code CHATMODEL_CACHE_TTL}: Claude batch system-message cache TTL, {@code 5m} (default) or {@code 1h}</li>
 * </ul>
 */
record ChatModelSettings(String url, String apiKey, String modelName, int maxTokens, String effort, String cacheTtl) {

    static final int DEFAULT_MAX_TOKENS = 100_000;
    static final String DEFAULT_CACHE_TTL = "5m";
    static final Duration TIMEOUT = Duration.ofMinutes(5);

    /** Structured output expected by compprov-analytics: {@code risk_score} (0-100) and {@code markdown_report}. */
    static final ResponseFormat RESPONSE_FORMAT = ResponseFormat.builder()
            .type(ResponseFormatType.JSON)
            .jsonSchema(JsonSchema.builder()
                    .name("PromptProcessingResult")
                    .rootElement(JsonObjectSchema.builder()
                            .addNumberProperty("risk_score")
                            .addStringProperty("markdown_report")
                            .required("risk_score", "markdown_report")
                            .build())
                    .build())
            .build();

    /**
     * @throws IllegalStateException if {@code CHATMODEL_URL} is not set or {@code CHATMODEL_MAXTOKENS} is not a positive integer
     */
    static ChatModelSettings fromEnv() {
        final var url = System.getenv("CHATMODEL_URL");
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("Env var CHATMODEL_URL is not set. CHATMODEL_URL, CHATMODEL_API_KEY and "
                    + "CHATMODEL_NAME are required to send prompts to a model");
        }
        return new ChatModelSettings(url,
                System.getenv("CHATMODEL_API_KEY"),
                System.getenv("CHATMODEL_NAME"),
                parseMaxTokens(System.getenv("CHATMODEL_MAXTOKENS")),
                System.getenv("CHATMODEL_EFFORT"),
                System.getenv().getOrDefault("CHATMODEL_CACHE_TTL", DEFAULT_CACHE_TTL));
    }

    boolean isAnthropic() {
        return url.toLowerCase(Locale.ROOT).contains("anthropic");
    }

    private static int parseMaxTokens(String value) {
        if (value == null || value.isBlank()) {
            return DEFAULT_MAX_TOKENS;
        }
        try {
            final var parsed = Integer.parseInt(value.trim());
            if (parsed > 0) {
                return parsed;
            }
        } catch (NumberFormatException ignored) {
            // reported below
        }
        throw new IllegalStateException("Env var CHATMODEL_MAXTOKENS must be a positive integer, got: " + value);
    }
}
