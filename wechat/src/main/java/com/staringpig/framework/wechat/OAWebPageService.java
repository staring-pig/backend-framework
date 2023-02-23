package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;

/**
 * @author ldh
 * time 2022-4-14 16:09
 */
public interface OAWebPageService {

    OAWebPageAccessCommand.Result webPageAccess(String code);

    String fetchOpenId(String code);

    OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(String webAccessToken, String openId);
}
