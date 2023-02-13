package com.staringpig.framework.wechat.client.api.corp.addressBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.corp.CorpResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
public class UserInfoQuery {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("UserId")
        private String userId;
        @JsonProperty("DeviceId")
        private String deviceId;
    }
}
