package com.staringpig.framework.wechat.offiaccount;

import lombok.Getter;

/**
 * 公众号应用
 */
@Getter
public abstract class OAApplication {
    /**
     * appId
     */
    private final String appId;
    /**
     * appSecret
     */
    private final String appSecret;
    /**
     * 令牌
     */
    private final String token;

    public OAApplication(String appId, String appSecret, String token) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.token = token;
    }
}
