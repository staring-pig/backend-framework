package com.staringpig.framework.wechat.client.api.offi.webpage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ldh
 * time 2022-4-14 14:53
 */
@Getter
@Setter
public class OAWebPageAccessTokenQuery {
    private String appid;
    private String secret;
    private String code;
    private final String grantType = "authorization_code";

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Result extends WechatServerResult {
        /**
         * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
         */
        @JsonProperty("access_token")
        private String accessToken;
        /**
         * access_token接口调用凭证超时时间，单位（秒）
         */
        @JsonProperty("expires_in")
        private Integer expiresIn;
        /**
         * 用户刷新access_token
         */
        @JsonProperty("refresh_token")
        private String refreshToken;
        /**
         * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
         */
        @JsonProperty("openid")
        private String openid;
        /**
         * 用户授权的作用域，使用逗号（,）分隔
         */
        @JsonProperty("scope")
        private String scope;
    }
}
