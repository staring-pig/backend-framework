package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.ScanEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class ScanEventHandler implements OAEventHandler<ScanEvent> {

    private final AllInOneHandler normalEventHandler;

    public ScanEventHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<ScanEvent> handleMessageType() {
        return ScanEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(ScanEvent event) {
        return normalEventHandler.onScan(event.getOAUser(), event.getEventKey(), event.getTicket());
    }
}
