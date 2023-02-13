package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessTokenQuery;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;

/**
 * @author ldh
 * time 2022-4-14 16:09
 */
public interface OAWebPageService {
    OAWebPageAccessTokenQuery.Result getWebPageAccessToken(OAWebPageAccessTokenQuery query);

    OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(String webAccessToken, String openId);
}
