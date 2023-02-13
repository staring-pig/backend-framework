package com.staringpig.framework.wechat.client.api.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 标签
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("tag_id")
    private String tag_id;

    @JsonProperty("tag_name")
    private String tagName;

    @JsonProperty("type")
    private Integer type;

}
