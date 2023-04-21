package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.ChatCompleting;
import com.staringpig.framework.ai.capability.Instruction;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.Usage;

import java.util.UUID;
import java.util.function.Consumer;

public abstract class ChatModel<T extends Usage> extends AIModel<T> implements ChatCompleting {

    @Override
    public String openChat() {
        return UUID.randomUUID().toString();
    }

    protected ChatContextStore chatContextStore;

    public ChatModel(String name, Costing<T> costing, ChatContextStore chatContextStore) {
        super(name, costing);
        this.chatContextStore = chatContextStore;
    }

    protected ChatContext context(String chat) {
        return this.chatContextStore.query(chat).orElse(new ChatContext(chat));
    }

    @Override
    public void instruct(String chat, Instruction instruction) {
        ChatContext context = this.context(chat);
        context.instruct(instruction);
        this.chatContextStore.save(context);
    }

    @Override
    public void chat(String chat, CompletingPrompt prompt, Consumer<ChatCompletion> onReply) {
        ChatContext context = this.context(chat);
        chat(context, prompt, reply -> {
            onReply.accept(new ChatCompletion(chat, reply));
            context.toDialogue(prompt, reply);
            this.chatContextStore.save(context);
        });
    }

    /**
     * 询问
     */
    protected abstract void chat(ChatContext context, CompletingPrompt prompt, Consumer<Completion> onReply);
}
