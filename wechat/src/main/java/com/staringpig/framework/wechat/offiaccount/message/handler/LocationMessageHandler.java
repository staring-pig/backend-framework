package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.LocationMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class LocationMessageHandler implements OrdinaryMessageHandler<LocationMessage> {

    private final AllInOneHandler normalEventHandler;

    public LocationMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<LocationMessage> handleMessageType() {
        return LocationMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(LocationMessage message) {
        return normalEventHandler.receiveLocation(message.getOAUser(), message.getLocationX(), message.getLocationY(),
                message.getScale(), message.getLabel());
    }
}
