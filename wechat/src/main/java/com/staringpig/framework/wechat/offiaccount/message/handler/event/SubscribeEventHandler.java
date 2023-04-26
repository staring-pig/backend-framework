package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.SubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;

import java.util.Optional;

public class SubscribeEventHandler implements OAEventHandler<SubscribeEvent> {

    private final OAUserRepository oaUserRepository;
    private final AllInOneHandler normalEventHandler;

    public SubscribeEventHandler(OAUserRepository oaUserRepository, AllInOneHandler normalEventHandler) {
        this.oaUserRepository = oaUserRepository;
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<SubscribeEvent> handleMessageType() {
        return SubscribeEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(SubscribeEvent event) {
        event.getOAUser().setSubscribed(true);
        oaUserRepository.saveIt(event.getOAUser());
        return normalEventHandler.onSubscribed(event.getOAUser());
    }
}
