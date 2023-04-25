package com.staringpig.framework.wechat.offiaccount.account;

import lombok.Getter;

/**
 * 公众号账号
 */
@Getter
public abstract class OAAccount {

    /**
     * 对应用户id
     */
    private final String openId;

    /**
     * 公众号的appId
     */
    private final String oaAppId;

    public OAAccount(String openId, String oaAppId) {
        this.openId = openId;
        this.oaAppId = oaAppId;
    }
}
