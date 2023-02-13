package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.offiaccount.OAMessageHandler;
import com.staringpig.framework.wechat.offiaccount.reply.ReplyMessage;

import java.util.Optional;

/**
 * 公众号事件处理器
 */
public interface OAEventHandler<T extends OAEventMessage> extends OAMessageHandler<T> {
    /**
     * 处理
     */
    @Override
    Optional<ReplyMessage> on(T event);
}
