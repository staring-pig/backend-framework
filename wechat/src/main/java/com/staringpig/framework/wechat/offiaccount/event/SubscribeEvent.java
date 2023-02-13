package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.account.OPAppAccount;

/**
 * 关注事件
 */
public class SubscribeEvent extends OAEventMessage {

    /**
     * openId
     */
    private final String openId;

    public String getOpenId() {
        return this.openId;
    }

    public SubscribeEvent(OPAppAccount opAppAccount, Long createTime) {
        super(opAppAccount, createTime, Type.subscribe);
        this.openId = opAppAccount.getOpenId();
    }

    public SubscribeEvent(String openId, Long createTime) {
        super(null, createTime, Type.subscribe);
        this.openId = openId;
    }
}
