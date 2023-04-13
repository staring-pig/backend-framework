package com.staringpig.framework.ai.capability;

import java.util.List;

/**
 * 聊天提示
 */
public interface ChatCompleting extends Completing {

    /**
     * 指使
     */
    void instructing(ChatInstruction instruction);

    /**
     * 聊天补全
     */
    ChatCompletion chat(ChatPrompt prompt);

    /**
     * chat 的 指令
     */
    class ChatInstruction extends Instruction {

        /**
         * 某一个chat会话
         */
        private String chat;
    }

    /**
     * 聊天文本
     */
    class ChatPrompt extends CompletingPrompt {

        /**
         * 某一个chat会话
         */
        private String chat;
    }


    class ChatCompletion extends Completion {
        /**
         * 某个chat
         */
        private String chat;
    }

    /**
     * 一次完整的对话
     */
    class Dialogue {
        /**
         * 指令，可能为空
         */
        private Instruction instruction;
        /**
         * 提示语
         */
        private CompletingPrompt prompt;
        /**
         * 完成
         */
        private Completion completion;
    }

    /**
     * 上下文
     */
    class ChatContext {
        /**
         * 某一个chat会话
         */
        private String chat;
        /**
         * 历史聊天信息
         */
        private List<Dialogue> dialogues;
    }
}
