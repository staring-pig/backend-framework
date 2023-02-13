package com.staringpig.framework.wechat.client.api.corp.external.tag;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.corp.CorpResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditExternalContactTagQuery {

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("external_userid")
    private String externalUserid;

    @JsonProperty("add_tag")
    private Set<String> addTag;

    @JsonProperty("remove_tag")
    private Set<String> removeTag;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class Result extends CorpResult {

    }

}
