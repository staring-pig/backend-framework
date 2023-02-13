package com.staringpig.framework.wechat.client.api.corp.addressBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.corp.CorpResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

public class UserQuery {

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        @JsonProperty("userid")
        private String userid;

        @JsonProperty("name")
        private String name;

        @JsonProperty("mobile")
        private String mobile;

        @JsonProperty("department")
        private List<Integer> department;

        @JsonProperty("order")
        private List<Integer> order;

        @JsonProperty("position")
        private String position;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("email")
        private String email;

        @JsonProperty("is_leader_in_dept")
        private List<Integer> isLeaderInDept;

        @JsonProperty("direct_leader")
        private List<String> directLeader;

        @JsonProperty("avatar")
        private String avatar;

        @JsonProperty("thumb_avatar")
        private String thumbAvatar;

        @JsonProperty("telephone")
        private String telephone;

        @JsonProperty("alias")
        private String alias;

        @JsonProperty("extattr")
        private Object extattr;

        @JsonProperty("status")
        private String status;

        @JsonProperty("qr_code")
        private String qrCode;

        @JsonProperty("external_profile")
        private Object externalProfile;

        @JsonProperty("external_position")
        private String externalPosition;

        @JsonProperty("address")
        private String address;

        @JsonProperty("open_userid")
        private String openUserid;

        @JsonProperty("main_department")
        private Integer mainDepartment;

    }

}
