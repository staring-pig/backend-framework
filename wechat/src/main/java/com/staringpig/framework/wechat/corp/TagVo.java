package com.staringpig.framework.wechat.corp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TagVo {

    private String groupName;

    private String tag_id;

    private String tagName;

    private Integer type;
}
