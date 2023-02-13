package com.staringpig.framework.wechat.client.api.corp.addressBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.corp.CorpResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class DepartmentUserQuery {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("userlist")
        private List<SimpleUser> userList;

    }
}
