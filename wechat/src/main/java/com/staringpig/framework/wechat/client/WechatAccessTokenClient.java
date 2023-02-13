package com.staringpig.framework.wechat.client;

import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import feign.Param;
import feign.RequestLine;

/**
 * 微信及小程序accessToken网关客户端
 */
public interface WechatAccessTokenClient {
    /**
     * 获取accessToken
     *
     * @return 结果
     */
    @RequestLine("POST /gw/wechat/cgi-bin/token?appid={appid}&secret={secret}&grant_type=client_credential")
    AccessTokenQuery.Result getAccessToken(@Param("appid") String appId, @Param("secret") String appSecret);
}
