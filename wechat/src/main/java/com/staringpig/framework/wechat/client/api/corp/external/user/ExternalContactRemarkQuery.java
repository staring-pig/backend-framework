package com.staringpig.framework.wechat.client.api.corp.external.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.corp.CorpResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ExternalContactRemarkQuery {

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("external_userid")
    private String externalUserid;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("description")
    private String description;

    @JsonProperty("remark_company")
    private String remarkCompany;

    @JsonProperty("remark_mobiles")
    private Set<String> remarkMobiles;

    @JsonProperty("remark_pic_mediaid")
    private String remarkPicMediaid;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    public static class Result extends CorpResult {

    }

}
