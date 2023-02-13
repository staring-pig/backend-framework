package com.staringpig.framework.wechat.client.api.offi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 公众号用户信息查询
 */
public class UserInfoQuery {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends WechatServerResult {
        @JsonProperty("subscribe")
        private Integer subscribe;

        @JsonProperty("openid")
        private String openid;

        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("sex")
        private Integer sex;

        @JsonProperty("language")
        private String language;

        @JsonProperty("city")
        private String city;

        @JsonProperty("province")
        private String province;

        @JsonProperty("country")
        private String country;

        @JsonProperty("headimgurl")
        private String headimgurl;

        @JsonProperty("subscribe_time")
        private Integer subscribeTime;

        @JsonProperty("unionid")
        private String unionid;

        @JsonProperty("remark")
        private String remark;

        @JsonProperty("groupid")
        private Integer groupid;

        @JsonProperty("tagid_list")
        private List<Integer> tagidList;

        @JsonProperty("subscribe_scene")
        private String subscribeScene;

        @JsonProperty("qr_scene")
        private Integer qrScene;

        @JsonProperty("qr_scene_str")
        private String qrSceneStr;
    }


}