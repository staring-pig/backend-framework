package com.staringpig.framework.wechat.client.api.offi.webpage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author ldh
 * time 2022-4-14 15:02
 */
public class OAWebPageUserInfoQuery {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Result extends WechatServerResult {
        /**
         * 用户的唯一标识
         */
        @JsonProperty("openid")
        private String openid;
        /**
         * 用户昵称
         */
        @JsonProperty("nickname")
        private String nickname;
        /**
         * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
         */
        @JsonProperty("sex")
        private Integer sex;
        /**
         * 用户个人资料填写的省份
         */
        @JsonProperty("province")
        private String province;
        @JsonProperty("city")
        private String city;
        @JsonProperty("country")
        private String country;
        /**
         * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
         */
        @JsonProperty("headimgurl")
        private String headimgurl;
        /**
         * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
         */
        @JsonProperty("privilege")
        private List<String> privilege;
        /**
         * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
         */
        @JsonProperty("unionid")
        private String unionid;
    }
}
