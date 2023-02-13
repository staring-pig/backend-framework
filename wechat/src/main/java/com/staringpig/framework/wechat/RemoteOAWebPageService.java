package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.OffiAccountClient;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessTokenQuery;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;

/**
 * @author ldh
 * time 2022-4-14 16:10
 */
public class RemoteOAWebPageService implements OAWebPageService {
    private final OffiAccountClient offiAccountClient;

    public RemoteOAWebPageService(OffiAccountClient offiAccountClient) {
        this.offiAccountClient = offiAccountClient;
    }

    @Override
    public OAWebPageAccessTokenQuery.Result getWebPageAccessToken(OAWebPageAccessTokenQuery query) {
        OAWebPageAccessTokenQuery.Result result = offiAccountClient.gzhWebPageAccessToken(query.getAppid(),
                query.getSecret(),
                query.getCode());
        result.isOK();
        return result;
    }

    @Override
    public OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(String webAccessToken, String openId) {
        OAWebPageUserInfoQuery.Result result = offiAccountClient.gzhWebPageUserInfo(webAccessToken, openId);
        result.isOK();
        return result;
    }
}
