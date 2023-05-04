package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.ScanSubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUserStore;

import java.util.Optional;

public class ScanSubscribeEventHandler implements OAEventHandler<ScanSubscribeEvent> {

    private final OAUserStore oaUserStore;
    private final AllInOneHandler normalEventHandler;

    public ScanSubscribeEventHandler(OAUserStore oaUserStore, AllInOneHandler normalEventHandler) {
        this.oaUserStore = oaUserStore;
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<ScanSubscribeEvent> handleMessageType() {
        return ScanSubscribeEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(ScanSubscribeEvent event) {
        event.getOAUser().setSubscribed(true);
        oaUserStore.saveIt(event.getOAUser());
        return normalEventHandler.onScanSubscribed(event.getOAUser(), event.getEventKey(), event.getTicket());
    }
}
