package com.staringpig.framework.ai.endpoint.openai.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.ai.usage.TokensUsage;
import lombok.Data;

/**
 * The OpenAI resources used by a request
 */
@Data
public class Usage {
    /**
     * The number of prompt tokens used.
     */
    @JsonProperty("prompt_tokens")
    long promptTokens;

    /**
     * The number of completion tokens used.
     */
    @JsonProperty("completion_tokens")
    long completionTokens;

    /**
     * The number of total tokens used
     */
    @JsonProperty("total_tokens")
    long totalTokens;

    public TokensUsage convert() {
        return new TokensUsage(promptTokens, completionTokens, totalTokens);
    }
}
