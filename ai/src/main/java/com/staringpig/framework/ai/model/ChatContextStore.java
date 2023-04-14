package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.ChatCompleting;

import java.util.Optional;

public interface ChatContextStore {

    /**
     * 有可能查不出来
     */
    Optional<ChatCompleting.ChatContext> query(String chat);

    /**
     * 保存chat
     */
    void save(ChatCompleting.ChatContext context);
}
