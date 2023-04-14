package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.ChatCompleting;
import com.staringpig.framework.ai.capability.Instruction;
import com.staringpig.framework.ai.usage.Usage;

import java.util.function.Consumer;

public abstract class ChatModel<T extends Usage> extends AIModel<T> implements ChatCompleting {
    protected ChatContextStore chatContextStore;

    public ChatModel(String name, ChatContextStore chatContextStore) {
        super(name);
        this.chatContextStore = chatContextStore;
    }

    protected ChatContext context(String chat) {
        return this.chatContextStore.query(chat).orElse(new ChatContext(chat));
    }

    @Override
    public void instruct(String chat, Instruction instruction) {
        this.context(chat).instruct(instruction);
    }

    @Override
    public void chat(String chat, CompletingPrompt prompt, Consumer<ChatCompletion> onReply) {
        chat(this.context(chat), prompt, reply -> {

            onReply.accept(new ChatCompletion(chat, reply));
        });
    }

    /**
     * 询问
     */
    protected abstract void chat(ChatContext context, CompletingPrompt prompt, Consumer<Completion> onReply);
}
