package com.staringpig.framework.wechat.corp;

import com.staringpig.framework.wechat.client.api.corp.Tag;
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
public class FollowUserVo {

    private String userid;

    private String remark;

    private String description;

    private Long createtime;

    private List<Tag> tags;

    private String remarkCorpName;

    private List<String> remarkMobiles;

    private Integer addWay;

    private String operUserid;

    private String state;
}
