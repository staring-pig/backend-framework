package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;

/**
 * 取消关注事件
 */
public final class UnsubscribeEvent extends OAEventMessage {

    public UnsubscribeEvent(OAAccount oaAccount, Long createTime) {
        super(oaAccount, createTime, Type.unsubscribe);
    }
}
