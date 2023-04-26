package com.staringpig.framework.wechat.offiaccount.message.handler.event;

import com.staringpig.framework.wechat.offiaccount.message.event.OAEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.UnsubscribeEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;

import java.util.Optional;

/**
 * 取消关注接口监听事件处理器
 */
public class UnsubscribeEventHandler implements OAEventHandler<UnsubscribeEvent> {

    private final OAUserRepository oaUserRepository;
    private final AllInOneHandler normalEventHandler;

    public UnsubscribeEventHandler(OAUserRepository oaUserRepository, AllInOneHandler normalEventHandler) {
        this.oaUserRepository = oaUserRepository;
        this.normalEventHandler = normalEventHandler;
    }

    @Override
    public Class<UnsubscribeEvent> handleMessageType() {
        return UnsubscribeEvent.class;
    }

    @Override
    public Optional<ReplyMessage> on(UnsubscribeEvent event) {
        event.getOAUser().setSubscribed(false);
        oaUserRepository.saveIt(event.getOAUser());
        return normalEventHandler.onUnSubscribed(event.getOAUser());
    }
}
