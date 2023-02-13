package com.staringpig.framework.wechat.client.api.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class ExternalContactListQuery {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("external_userid")
        private List<String> externalUserid;

    }
}
