package com.staringpig.framework.ai.capability;

import com.staringpig.framework.support.AllInOne;
import lombok.Getter;

import java.util.ArrayList;
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
        chat(AllInOne.STRING_EMPTY, prompt, onReply::accept);
    }

    class ChatCompletion extends Completion {
        /**
         * 某个chat
         */
        @Getter
        private final String chat;

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
        @Getter
        private List<Instruction> instructions;
        /**
         * 提示语
         */
        @Getter
        private CompletingPrompt prompt;
        /**
         * 完成
         */
        @Getter
        private Completion completion;
    }

    /**
     * 上下文
     */
    class ChatContext {
        /**
         * 某一个chat会话
         */
        @Getter
        private final String chat;
        /**
         * 历史对话
         */
        @Getter
        private List<Dialogue> dialogues;
        /**
         * 新指令
         */
        @Getter
        private List<Instruction> newInstructions;

        public ChatContext(String chat) {
            this.chat = chat;
            this.dialogues = new ArrayList<>();
            this.newInstructions = new ArrayList<>();
        }

        public void instruct(Instruction instruction) {
            this.newInstructions.add(instruction);
        }

        public void toDialogue(CompletingPrompt prompt, Completion completion) {
            Dialogue newDialogue = new Dialogue();
            if (AllInOne.isNotEmpty(this.newInstructions)) {
                newDialogue.instructions = this.newInstructions;
            }
            newDialogue.prompt = prompt;
            newDialogue.completion = completion;
            this.dialogues.add(newDialogue);
        }
    }
}
