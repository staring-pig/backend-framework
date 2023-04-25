package com.staringpig.framework.wechat.miniprogram;

import lombok.Getter;

public abstract class MiniProgram {
    /**
     * appId
     */
    @Getter
    private final String appId;
    /**
     * appSecret
     */
    @Getter
    private final String appSecret;

    private final MPEndpoint mpEndpoint;

    protected MiniProgram(String appId, String appSecret, MPEndpoint mpEndpoint) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.mpEndpoint = mpEndpoint;
    }

    public MiniProgram.Session login(String code) {
        return mpEndpoint.login(this.appId, this.appSecret, code);
    }

    @Getter
    static class Session {

        /**
         * 对应用户id
         */
        private final String openId;
        /**
         * 开放平台id
         */
        private final String opAccountId;
        /**
         * key
         */
        private String sessionKey;

        public Session(String openId, String opAccountId) {
            this.openId = openId;
            this.opAccountId = opAccountId;
        }

        public Session(String openId, String opAccountId, String sessionKey) {
            this.openId = openId;
            this.opAccountId = opAccountId;
            this.sessionKey = sessionKey;
        }
    }
}
