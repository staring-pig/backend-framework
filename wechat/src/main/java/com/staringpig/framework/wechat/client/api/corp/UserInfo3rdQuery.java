package com.staringpig.framework.wechat.client.api.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class UserInfo3rdQuery {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("CorpId")
        private String corpId;

        @JsonProperty("UserId")
        private String userId;

        @JsonProperty("DeviceId")
        private String deviceId;

        @JsonProperty("user_ticket")
        private String userTicket;

        @JsonProperty("expires_in")
        private Long expiresIn;

        @JsonProperty("open_userid")
        private String openUserid;
    }
}
