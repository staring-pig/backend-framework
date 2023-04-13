package com.staringpig.framework.ai.capability;

import lombok.Getter;

/**
 * 文本完成能力，一般是通过一个prompt来完成文本
 */
public interface Completing extends Capability {

    /**
     * 补全
     */
    Completion complete(CompletingPrompt prompt);

    /**
     * 补全的提示语
     */
    class CompletingPrompt extends Prompt {

        @Getter
        private String user;
    }

    /**
     * 完成后的内容
     */
    abstract class Completion {
        /**
         * 内容
         */
        private String content;
    }
}
