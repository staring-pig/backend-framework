package com.staringpig.framework.wechat.client.api.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class JsapiTicketQuery {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("ticket")
        private String ticket;

        @JsonProperty("expires_in")
        private Long expiresIn;
    }
}
