package com.staringpig.framework.wechat.client.api.offi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * 微信小程序code转session命令
 */
public class GZHAccessTokenQuery {


    /**
     * 结果
     */
    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends WechatServerResult {
        /**
         * session-key
         */
        @JsonProperty("access_token")
        private String accessToken;
        /**
         * 失效时间间隔
         */
        @JsonProperty("expires_in")
        private Long expiresIn;
    }
}
