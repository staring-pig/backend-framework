package com.staringpig.framework.wechat.miniprogram.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信小程序code转session命令
 */
public class Code2SessionQuery {


    /**
     * 结果
     */
    @Setter
    @Getter
    @ToString(callSuper = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends ApiResponse {
        /**
         * session-key
         */
        @JsonProperty("session_key")
        private String sessionKey;
        /**
         * 公众号账户id
         */
        @JsonProperty("openid")
        private String openId;
        /**
         * 公众平台账户id
         */
        @JsonProperty("unionid")
        private String unionId;
    }
}
