package com.staringpig.framework.wechat.corp;


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
public class FollowInfoVo extends FollowUserVo {

    private List<String> tagId;
}
