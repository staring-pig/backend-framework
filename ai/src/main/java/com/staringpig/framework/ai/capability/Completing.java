package com.staringpig.framework.ai.capability;

import lombok.Getter;

import java.util.function.Consumer;

/**
 * 文本完成能力，一般是通过一个prompt来完成文本
 */
public interface Completing extends Capability {

    /**
     * 补全
     */
    void complete(CompletingPrompt prompt, Consumer<Completion> onReply);

    /**
     * 补全的提示语
     */
    class CompletingPrompt extends Prompt {

        @Getter
        private final String user;

        public CompletingPrompt(String user, String content) {
            super(content);
            this.user = user;
        }
    }

    /**
     * 完成后的内容
     */
    class Completion {
        /**
         * 内容
         */
        @Getter
        private String content;

        public Completion(Completion completion) {
            this.content = completion.content;
        }

        public Completion(String content) {
            this.content = content;
        }
    }
}
