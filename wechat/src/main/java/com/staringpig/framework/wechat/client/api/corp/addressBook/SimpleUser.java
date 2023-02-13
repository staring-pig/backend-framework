package com.staringpig.framework.wechat.client.api.corp.addressBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUser {

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("department")
    private List<Integer> department;

    @JsonProperty("open_userid")
    private String openUserid;
}
