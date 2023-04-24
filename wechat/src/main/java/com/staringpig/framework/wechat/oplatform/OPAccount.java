package com.staringpig.framework.wechat.oplatform;

import lombok.Getter;

/**
 * 开放平台用户账户
 */
public abstract class OPAccount {

    @Getter
    private final String unionId;
    @Getter
    private final String opAppId;

    public OPAccount(String unionId, String opAppId) {
        this.unionId = unionId;
        this.opAppId = opAppId;
    }
}
