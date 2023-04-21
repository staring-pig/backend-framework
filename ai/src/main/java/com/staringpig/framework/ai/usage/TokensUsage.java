package com.staringpig.framework.ai.usage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.StringJoiner;

/**
 * tokens的使用
 */
@Getter
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

    @Override
    public String toString() {
        return new StringJoiner(", ", TokensUsage.class.getSimpleName() + "[", "]")
                .add("promptTokens=" + promptTokens)
                .add("completionTokens=" + completionTokens)
                .add("totalTokens=" + totalTokens)
                .toString();
    }
}
