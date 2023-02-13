package com.staringpig.framework.wechat.corp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalContactVo {

    private ExternalUserVo externalUser;

    private FollowInfoVo followInfo;
}
