package com.staringpig.framework.wechat.offiaccount.message;

import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

/**
 * 公众号消息处理
 */
public interface OAMessageHandler<T extends OAMessage> {
    /**
     * 可以处理的消息类型
     */
    Class<T> handleMessageType();

    /**
     * 做一次类型转换
     *
     * @param oaMessage 消息
     * @return 回复
     */
    @SuppressWarnings("unchecked")
    default Optional<ReplyMessage> onUnknownTypeMessage(OAMessage oaMessage) {
        if (oaMessage.getClass().equals(handleMessageType())) {
            return this.on((T) oaMessage);
        }
        return Optional.empty();
    }

    /**
     * 处理
     */
    Optional<ReplyMessage> on(T message);
}
