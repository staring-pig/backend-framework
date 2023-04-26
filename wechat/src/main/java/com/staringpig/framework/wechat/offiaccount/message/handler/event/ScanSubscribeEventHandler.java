package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.ScanSubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;

import java.util.Optional;

public class ScanSubscribeEventHandler implements OAEventHandler<ScanSubscribeEvent> {

    private final OAUserRepository oaUserRepository;
    private final AllInOneHandler normalEventHandler;

    public ScanSubscribeEventHandler(OAUserRepository oaUserRepository, AllInOneHandler normalEventHandler) {
        this.oaUserRepository = oaUserRepository;
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<ScanSubscribeEvent> handleMessageType() {
        return ScanSubscribeEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(ScanSubscribeEvent event) {
        event.getOAUser().setSubscribed(true);
        oaUserRepository.saveIt(event.getOAUser());
        return normalEventHandler.onScanSubscribed(event.getOAUser(), event.getEventKey(), event.getTicket());
    }
}
