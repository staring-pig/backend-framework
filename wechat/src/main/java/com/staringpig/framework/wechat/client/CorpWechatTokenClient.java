package com.staringpig.framework.wechat.client;

import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.corp.JsapiTicketQuery;
import feign.Param;
import feign.RequestLine;

/**
 * @author guoqiang
 * @date 2021-12-9 17:23
 * @description 企业微信token及ticket客户端
 **/
public interface CorpWechatTokenClient {

    /**
     * 获取 accessToken
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/gettoken?corpid={corpid}&corpsecret={corpsecret}")
    AccessTokenQuery.Result getAccessToken(@Param("corpid") String corpId, @Param("corpsecret") String secret);

    /**
     * 获取企业 JsapiTicket
     *
     * @return 结果
     */
    @RequestLine("POST /cgi-bin/get_jsapi_ticket?access_token={accessToken}&corpid={corpid}&corpsecret={corpsecret}")
    JsapiTicketQuery.Result getJsapiTicket(@Param("accessToken") String accessToken, @Param("corpid") String corpId, @Param("corpsecret") String secret);
}
