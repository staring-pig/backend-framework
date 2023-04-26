package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.TextMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.VideoMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class VideoMessageHandler implements OrdinaryMessageHandler<VideoMessage> {

    private final AllInOneHandler normalEventHandler;

    public VideoMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<VideoMessage> handleMessageType() {
        return VideoMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(VideoMessage message) {
        return normalEventHandler.receiveVideo(message.getOAUser(), message.getMediaId(), message.getThumbMediaId());
    }
}
