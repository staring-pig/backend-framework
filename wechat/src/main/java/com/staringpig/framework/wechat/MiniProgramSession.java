package com.staringpig.framework.wechat;

import lombok.Getter;

/**
 * 小程序登陆session
 * <p>
 * 小程序在获取微信用户信息的时候（存储在微信后台），需要先登陆微信
 *
 * @author niumy
 */
@Getter
public class MiniProgramSession {

    /**
     * 对应用户id
     */
    private final String openId;
    /**
     * 对应的应用
     */
    private final OPApplication application;
    /**
     * 开放平台id
     */
    private final String opAccountId;
    /**
     * key
     */
    private String sessionKey;

    public MiniProgramSession(String openId, OPApplication application, String opAccountId) {
        this.openId = openId;
        this.application = application;
        this.opAccountId = opAccountId;
    }

    public MiniProgramSession(String openId, OPApplication application, String opAccountId, String sessionKey) {
        this.openId = openId;
        this.application = application;
        this.opAccountId = opAccountId;
        this.sessionKey = sessionKey;
    }
}
