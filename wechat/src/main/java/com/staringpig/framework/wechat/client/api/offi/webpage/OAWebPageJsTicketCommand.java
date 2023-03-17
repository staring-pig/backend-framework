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
public class OAWebPageJsTicketCommand {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Result extends WechatServerResult {
        /**
         * ticket
         */
        private String ticket;
        /**
         * access_token接口调用凭证超时时间，单位（秒）
         */
        @JsonProperty("expires_in")
        private Integer expiresIn;
    }
}
