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
public class ExternalUser {

    @JsonProperty("external_userid")
    private String externalUserid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("gender")
    private Integer gender;

    @JsonProperty("unionid")
    private String unionid;

    @JsonProperty("position")
    private String position;

    @JsonProperty("corp_name")
    private String corpName;

    @JsonProperty("corp_full_name")
    private String corpFullName;

    @JsonProperty("external_profile")
    private Object externalProfile;

    @JsonProperty("follow_user")
    private List<FollowUser> followUser;

    @JsonProperty("next_cursor")
    private String nextCursor;
}
