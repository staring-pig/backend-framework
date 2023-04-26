package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.ImageMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class ImageMessageHandler implements OrdinaryMessageHandler<ImageMessage> {

    private final AllInOneHandler normalEventHandler;

    public ImageMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<ImageMessage> handleMessageType() {
        return ImageMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(ImageMessage message) {
        return normalEventHandler.receiveImage(message.getOAUser(), message.getPicUrl(), message.getMediaId());
    }
}
