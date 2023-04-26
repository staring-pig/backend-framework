package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.UpLocationEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class UpLocationEventHandler implements OAEventHandler<UpLocationEvent> {

    private final AllInOneHandler normalEventHandler;

    public UpLocationEventHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<UpLocationEvent> handleMessageType() {
        return UpLocationEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(UpLocationEvent event) {
        return normalEventHandler.onUpLocation(event.getOAUser(), event.getLatitude(), event.getLongitude(),
                event.getPrecision());
    }
}
