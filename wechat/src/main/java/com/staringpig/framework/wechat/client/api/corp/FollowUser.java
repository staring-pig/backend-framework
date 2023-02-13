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
public class FollowUser {

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("description")
    private String description;

    @JsonProperty("createtime")
    private Long createtime;

    @JsonProperty("tags")
    private List<Tag> tags;

    @JsonProperty("remark_corp_name")
    private String remarkCorpName;

    @JsonProperty("remark_mobiles")
    private List<String> remarkMobiles;

    @JsonProperty("add_way")
    private Integer addWay;

    @JsonProperty("oper_userid")
    private String operUserid;

    @JsonProperty("state")
    private String state;
}
