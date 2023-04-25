package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.Endpoint;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.ServerException;
import com.staringpig.framework.wechat.offiaccount.api.UserInfoQuery;

import java.io.IOException;
import java.util.Objects;

@Endpoint
public class OAEndpoint {

    private final OffiAccountAPI api;
    private final AccessTokenEndpoint accessTokenEndpoint;

    public OAEndpoint(OffiAccountAPI api, AccessTokenEndpoint accessTokenEndpoint) {
        this.api = api;
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    public OffiAccount.UserInfo userInfo(String appId, String appSecret, String openId) {
        String accessToken = accessTokenEndpoint.accessToken(appId, appSecret);
        UserInfoQuery.Result result;
        try {
            result = api.getUserInfo(accessToken, openId).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
        return OffiAccount.UserInfo.builder()
                .openid(result.getOpenid())
                .city(result.getCity())
                .country(result.getCountry())
                .groupId(result.getGroupid())
                .headImgUrl(result.getHeadimgurl())
                .language(result.getLanguage())
                .nickname(result.getNickname())
                .province(result.getProvince())
                .qrScene(result.getQrScene())
                .qrSceneStr(result.getQrSceneStr())
                .remark(result.getRemark())
                .sex(result.getSex())
                .subscribeScene(result.getSubscribeScene())
                .subscribeTime(result.getSubscribeTime())
                .tagIds(result.getTagidList())
                .unionId(result.getUnionid())
                .build();
    }
}
