package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.OffiAccountClient;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;
import com.staringpig.framework.wechat.offiaccount.OffiAccountApplication;

/**
 * @author ldh
 * time 2022-4-14 16:10
 */
public class RemoteOAWebPageService implements OAWebPageService {

    private final OffiAccountApplication offiAccountApplication;
    private final OffiAccountClient offiAccountClient;

    public RemoteOAWebPageService(OffiAccountApplication offiAccountApplication,
                                  OffiAccountClient offiAccountClient) {
        this.offiAccountApplication = offiAccountApplication;
        this.offiAccountClient = offiAccountClient;
    }

    @Override
    public OAWebPageAccessCommand.Result webPageAccess(String code) {
        OAWebPageAccessCommand.Result result = offiAccountClient.oaWebPageAccessToken(
                offiAccountApplication.getAppId(),offiAccountApplication.getAppSecret(), code);
        result.isOK();
        return result;
    }

    @Override
    public String fetchOpenId(String code) {
        return this.webPageAccess(code).getOpenid();
    }

    @Override
    public OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(String webAccessToken, String openId) {
        OAWebPageUserInfoQuery.Result result = offiAccountClient.gzhWebPageUserInfo(webAccessToken, openId);
        result.isOK();
        return result;
    }
}
