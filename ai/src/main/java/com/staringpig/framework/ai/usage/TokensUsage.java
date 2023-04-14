package com.staringpig.framework.ai.usage;

import lombok.AllArgsConstructor;

/**
 * tokens的使用
 */
@AllArgsConstructor
public class TokensUsage extends Usage {

    /**
     * 提示语tokens
     */
    private long promptTokens;
    /**
     * 补全语tokens
     */
    private long completionTokens;
    /**
     * 总tokens
     */
    private long totalTokens;
}
