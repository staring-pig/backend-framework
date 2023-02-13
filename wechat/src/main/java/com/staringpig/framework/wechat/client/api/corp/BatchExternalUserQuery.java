package com.staringpig.framework.wechat.client.api.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BatchExternalUserQuery {

    @JsonProperty("userid_list")
    private List<String> useridList;

    @JsonProperty("cursor")
    private String cursor;

    @JsonProperty("limit")
    private Integer limit = 100;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("external_contact_list")
        private List<ExternalContact> externalContactList;

    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalContact {

        @JsonProperty("external_contact")
        private ExternalUser externalUser;

        @JsonProperty("follow_info")
        private FollowInfo followInfo;

    }

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FollowInfo extends FollowUser {

        @JsonProperty("tag_id")
        private List<String> tagId;

    }
}
