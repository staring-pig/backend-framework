package com.staringpig.framework.wechat.corp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalUserVo {

    private String externalUserid;

    private String name;

    private String avatar;

    private Integer type;

    private Integer gender;

    private String unionid;

    private String position;

    private String corpName;

    private String corpFullName;

    private Object externalProfile;

    private List<FollowUserVo> followUser;

    private String nextCursor;
}
