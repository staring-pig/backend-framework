package com.staringpig.framework.wechat.offiaccount.account;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 公众号账号
 */
@Getter
public abstract class OAAccount {

    /**
     * 对应用户id
     */
    private final String openId;

    /**
     * 公众号的appId
     */
    private final String oaAppId;
    /**
     * 昵称
     */
    @Setter
    private String nickname;
    /**
     * 性别
     */
    @Setter
    private Integer sex;
    /**
     * 语言
     */
    @Setter
    private String language;
    /**
     * 城市
     */
    @Setter
    private String city;
    /**
     * 地区
     */
    @Setter
    private String province;
    /**
     * 国家
     */
    @Setter
    private String country;
    /**
     * 头像
     */
    @Setter
    private String headImgUrl;
    /**
     * 监听时间
     */
    @Setter
    private Integer subscribeTime;
    /**
     * 标记
     */
    @Setter
    private String remark;
    /**
     * 组ID
     */
    @Setter
    private Integer groupId;
    /**
     * 标签
     */
    @Setter
    private List<Integer> tagIds;
    /**
     * 订阅场景
     */
    @Setter
    private String subscribeScene;
    /**
     * 二维码
     */
    @Setter
    private Integer qrScene;
    /**
     * 二维码内容
     */
    @Setter
    private String qrSceneStr;

    public OAAccount(String openId, String oaAppId) {
        this.openId = openId;
        this.oaAppId = oaAppId;
    }
}
