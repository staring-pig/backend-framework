package com.staringpig.framework.wechat.offiaccount.message.dispatcher;

import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Collection;
import java.util.Optional;

/**
 * 公众号消息派发器
 */
public interface OAMessageDispatcher {

    /**
     * 派发消息
     *
     * @param message 消息
     * @return 消息回复
     */
    Optional<ReplyMessage> dispatch(OAMessage message);

    /**
     * 注册消息处理器
     *
     * @param oaMessageHandlers 消息处理器
     */
    void registerMessageHandler(Collection<OAMessageHandler<? extends OAMessage>> oaMessageHandlers);
}
