package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.LinkMessage;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.OrdinaryMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

import java.util.Optional;

public class LinkMessageHandler implements OrdinaryMessageHandler<LinkMessage> {

    private final AllInOneHandler normalEventHandler;

    public LinkMessageHandler(AllInOneHandler normalEventHandler) {
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<LinkMessage> handleMessageType() {
        return LinkMessage.class;
    }

    @Override
    public Optional<ReplyMessage> on(LinkMessage message) {
        return normalEventHandler.receiveLink(message.getOAUser(), message.getTitle(), message.getDescription(),
                message.getUrl());
    }
}
