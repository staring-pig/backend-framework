package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.account.OPAppAccount;

/**
 * 取消关注事件
 */
public final class UnsubscribeEvent extends OAEventMessage {

    public UnsubscribeEvent(OPAppAccount opAppAccount, Long createTime) {
        super(opAppAccount, createTime, Type.unsubscribe);
    }
}
