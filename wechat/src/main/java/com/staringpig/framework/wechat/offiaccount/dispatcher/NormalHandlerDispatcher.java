package com.staringpig.framework.wechat.offiaccount.dispatcher;

import com.staringpig.framework.wechat.offiaccount.OAMessage;
import com.staringpig.framework.wechat.offiaccount.reply.ReplyMessage;

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
