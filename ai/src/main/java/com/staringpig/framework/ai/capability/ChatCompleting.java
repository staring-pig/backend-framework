package com.staringpig.framework.ai.capability;

/**
 * 聊天提示
 */
public interface ChatCompleting extends Completing {

    /**
     * 指使
     */
    void instructing(Instruction instruction);

    /**
     * 聊天补全
     */
    Completion chat(ChatPrompt prompt);

    /**
     * 聊天文本
     */
    abstract class ChatPrompt extends Prompt {

    }
}
