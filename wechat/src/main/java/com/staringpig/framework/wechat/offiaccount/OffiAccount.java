package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import com.staringpig.framework.wechat.oplatform.OPUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 公众号应用
 */

public class OffiAccount {
    /**
     * appId
     */
    @Getter
    private final String appId;
    /**
     * appSecret
     */
    @Getter
    private final String appSecret;
    /**
     * 令牌
     */
    @Getter
    private final String token;

    private final OAEndpoint endpoint;

    public OffiAccount(String appId, String appSecret, String token, OAEndpoint endpoint) {
        this.appId = appId;
        this.appSecret = appSecret;
        this.token = token;
        this.endpoint = endpoint;
    }

    public UserInfo userInfo(String openId) {
        return endpoint.userInfo(this.appId, this.appSecret, openId);
    }

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfo {

        private String openid;

        private String nickname;

        private Integer sex;

        private String language;

        private String city;

        private String province;

        private String country;

        private String headImgUrl;

        private Integer subscribeTime;

        private String unionId;

        private String remark;

        private Integer groupId;

        private List<Integer> tagIds;

        private String subscribeScene;

        private Integer qrScene;

        private String qrSceneStr;

        public OAUser convert(String appId) {
            OAUser account = new OAUser(this.getOpenid(), appId, new OPUser(this.unionId));
            account.setCity(this.getCity());
            account.setCountry(this.getCountry());
            account.setLanguage(this.getLanguage());
            account.setNickname(this.getNickname());
            account.setProvince(this.getProvince());
            account.setGroupId(this.getGroupId());
            account.setHeadImgUrl(this.getHeadImgUrl());
            account.setQrScene(this.getQrScene());
            account.setQrSceneStr(this.getQrSceneStr());
            account.setRemark(this.getRemark());
            account.setSex(this.getSex());
            account.setSubscribed(true);
            account.setSubscribeScene(this.getSubscribeScene());
            account.setSubscribeTime(this.getSubscribeTime());
            account.setTagIds(this.getTagIds());
            return account;
        }
    }
}
