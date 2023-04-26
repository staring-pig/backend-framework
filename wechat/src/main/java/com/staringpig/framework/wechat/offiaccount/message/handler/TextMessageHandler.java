package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.ShortVideoMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.TextMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class TextMessageHandler implements OrdinaryMessageHandler<TextMessage> {

    private final AllInOneHandler normalEventHandler;

    public TextMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<TextMessage> handleMessageType() {
        return TextMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(TextMessage message) {
        return normalEventHandler.receiveText(message.getOAUser(), message.getContent());
    }
}
