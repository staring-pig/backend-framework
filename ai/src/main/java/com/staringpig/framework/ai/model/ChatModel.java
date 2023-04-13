package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.ChatCompleting;

public abstract class ChatModel extends AIModel implements ChatCompleting {

    public ChatModel(String name) {
        super(name);
    }

    abstract ChatContext context(String chat);
}
