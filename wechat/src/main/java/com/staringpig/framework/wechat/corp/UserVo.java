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
public class UserVo {

    private String userid;

    private String name;

    private String mobile;

    private List<Integer> department;

    private List<Integer> order;

    private String position;

    private String gender;

    private String email;

    private List<Integer> isLeaderInDept;

    private List<String> directLeader;

    private String avatar;

    private String thumbAvatar;

    private String telephone;

    private String alias;

    private Object extattr;

    private String status;

    private String qrCode;

    private Object externalProfile;

    private String externalPosition;

    private String address;

    private String openUserid;

    private Integer mainDepartment;
}
