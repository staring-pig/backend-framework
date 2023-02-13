package com.staringpig.framework.wechat;

import lombok.Getter;
import lombok.Setter;

/**
 * 开放平台应用
 *
 * @author niumy
 */
@Getter
public abstract class OPApplication {

    /**
     * appId
     */
    @Setter
    private String appId;
    /**
     * appSecret
     */
    @Setter
    private String appSecret;
    /**
     * 应用类型
     */
    private final OPAppType type;
    /**
     * 令牌
     */
    @Setter
    private String token;

    protected OPApplication(String appId, OPAppType type) {
        this.appId = appId;
        this.type = type;
    }

    public OPApplication(String appId, String appSecret, OPAppType type, String token) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.type = type;
        this.token = token;
    }

    /**
     * 一个应用的唯一性确定
     */
    public String getId() {
        return this.appId;
    }
}
