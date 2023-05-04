package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.SubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUserStore;

import java.util.Optional;

public class SubscribeEventHandler implements OAEventHandler<SubscribeEvent> {

    private final OAUserStore oaUserStore;
    private final AllInOneHandler normalEventHandler;

    public SubscribeEventHandler(OAUserStore oaUserStore, AllInOneHandler normalEventHandler) {
        this.oaUserStore = oaUserStore;
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<SubscribeEvent> handleMessageType() {
        return SubscribeEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(SubscribeEvent event) {
        event.getOAUser().setSubscribed(true);
        oaUserStore.saveIt(event.getOAUser());
        return normalEventHandler.onSubscribed(event.getOAUser());
    }
}
