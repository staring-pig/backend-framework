package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;

/**
 * 关注事件
 */
public class SubscribeEvent extends OAEventMessage {

    public SubscribeEvent(OAUser OAUser, Long createTime) {
        super(OAUser, createTime, Type.subscribe);
    }
}
