package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;

/**
 * 取消关注事件
 */
public final class UnsubscribeEvent extends OAEventMessage {

    public UnsubscribeEvent(OAUser OAUser, Long createTime) {
        super(OAUser, createTime, Type.unsubscribe);
    }
}
