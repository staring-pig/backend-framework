package com.staringpig.framework.starters.ai;

import com.staringpig.framework.ai.capability.ChatCompleting;
import com.staringpig.framework.ai.model.ChatContextStore;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Optional;

public class CachedChatContextStore implements ChatContextStore {

    private final Cache cache;

    public CachedChatContextStore(CacheManager cacheManager) {
        this.cache = cacheManager.getCache(this.getClass().getName());
    }

    @Override
    public Optional<ChatCompleting.ChatContext> query(String chat) {
        return Optional.ofNullable(this.cache.get(chat, ChatCompleting.ChatContext.class));
    }

    @Override
    public void save(ChatCompleting.ChatContext context) {
        this.cache.put(context.getChat(), context);
    }
}
