package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.ViewEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class ViewEventHandler implements OAEventHandler<ViewEvent> {

    private final AllInOneHandler normalEventHandler;

    public ViewEventHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<ViewEvent> handleMessageType() {
        return ViewEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(ViewEvent event) {
        return normalEventHandler.onView(event.getOAUser(), event.getUrl());
    }
}
