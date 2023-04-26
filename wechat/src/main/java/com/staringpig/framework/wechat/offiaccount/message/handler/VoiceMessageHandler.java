package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.VoiceMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class VoiceMessageHandler implements OrdinaryMessageHandler<VoiceMessage> {

    private final AllInOneHandler normalEventHandler;

    public VoiceMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<VoiceMessage> handleMessageType() {
        return VoiceMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(VoiceMessage message) {
        return normalEventHandler.receiveVoice(message.getOAUser(), message.getMediaId(), message.getFormat(),
                message.getRecognition());
    }
}
