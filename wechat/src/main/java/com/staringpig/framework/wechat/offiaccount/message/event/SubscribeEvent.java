package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;

/**
 * 关注事件
 */
public class SubscribeEvent extends OAEventMessage {

    public SubscribeEvent(OAAccount oaAccount, Long createTime) {
        super(oaAccount, createTime, Type.subscribe);
    }
}
