package com.staringpig.framework.wechat.offiaccount.message.dispatcher;

import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class NormalHandlerDispatcher extends OneToOneMessageDispatcher implements OAMessageDispatcher {

    @Override
    public Optional<ReplyMessage> dispatch(OAMessage message) {
        if (message == null || !super.messageHandlerMap.containsKey(message.getClass())) {
            return Optional.empty();
        }
        return super.messageHandlerMap.get(message.getClass()).onUnknownTypeMessage(message);
    }
}
