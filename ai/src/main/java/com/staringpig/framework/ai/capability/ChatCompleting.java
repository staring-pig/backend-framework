package com.staringpig.framework.ai.capability;

import net.dreamlu.mica.core.utils.StringPool;

import java.util.List;
import java.util.function.Consumer;

/**
 * 聊天提示
 */
public interface ChatCompleting extends Completing {

    /**
     * 开启一个会话
     */
    String openChat();

    /**
     * 指使
     */
    void instruct(String chat, Instruction instruction);

    /**
     * 聊天补全
     */
    void chat(String chat, CompletingPrompt prompt, Consumer<ChatCompletion> onReply);

    /**
     * 不带chat的自动补全方式
     */
    @Override
    default void complete(CompletingPrompt prompt, Consumer<Completion> onReply) {
        chat(StringPool.EMPTY, prompt, onReply::accept);
    }

    class ChatCompletion extends Completion {
        /**
         * 某个chat
         */
        private String chat;

        public ChatCompletion(String chat, Completion completion) {
            super(completion);
            this.chat = chat;
        }
    }

    /**
     * 一次完整的对话
     */
    class Dialogue {
        /**
         * 指令，可能为空
         */
        private List<Instruction> instructions;
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
         * 历史对话
         */
        private List<Dialogue> dialogues;
        /**
         * 新指令
         */
        private List<Instruction> newInstructions;

        public ChatContext(String chat) {
            this.chat = chat;
        }

        public void instruct(Instruction instruction) {
            this.newInstructions.add(instruction);
        }
    }
}
