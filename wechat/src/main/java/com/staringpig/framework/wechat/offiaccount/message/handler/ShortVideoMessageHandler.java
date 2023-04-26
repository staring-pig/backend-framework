package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.ShortVideoMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class ShortVideoMessageHandler implements OrdinaryMessageHandler<ShortVideoMessage> {

    private final AllInOneHandler normalEventHandler;

    public ShortVideoMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<ShortVideoMessage> handleMessageType() {
        return ShortVideoMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(ShortVideoMessage message) {
        return normalEventHandler.receiveShortVideo(message.getOAUser(), message.getMediaId(),
                message.getThumbMediaId());
    }
}
