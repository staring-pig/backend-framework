package com.staringpig.framework.wechat.client;

import com.staringpig.framework.wechat.client.api.offi.GZHAccessTokenQuery;
import com.staringpig.framework.wechat.client.api.offi.MenuAdd;
import com.staringpig.framework.wechat.client.api.offi.MenuQuery;
import com.staringpig.framework.wechat.client.api.offi.SendTemplateMessageCommand;
import com.staringpig.framework.wechat.client.api.offi.UserInfoQuery;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessTokenQuery;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;
import com.staringpig.framework.wechat.offiaccount.menu.OffiAccountMenu;
import feign.Param;
import feign.RequestLine;

/**
 * 公众号客户端
 */
public interface OffiAccountClient {

    String URL = "https://api.weixin.qq.com";

    /**
     * GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/access-token/auth.getAccessToken.html
     */
    @RequestLine("POST /cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}")
    GZHAccessTokenQuery.Result getAccessToken(@Param("appid") String appid,
                                              @Param("secret") String secret);

    /**
     * http请求方式: POST https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    @RequestLine("POST /cgi-bin/user/info?lang=zh_CN&access_token={accessToken}&openid={openid}")
    UserInfoQuery.Result getUserInfo(@Param("accessToken") String accessToken, @Param("openid") String openId);

    /**
     * POST https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
     */
    @RequestLine("POST /cgi-bin/com.staringpig.backendframework.message/template/send?access_token={accessToken}")
    SendTemplateMessageCommand.Result sendTemplateMessage(@Param("accessToken") String accessToken,
                                                          SendTemplateMessageCommand<?> command);

    /**
     * 创建菜单
     */
    @RequestLine("POST /cgi-bin/menu/create?access_token={accessToken}")
    MenuAdd.Result createMenu(@Param("accessToken") String accessToken, OffiAccountMenu offiAccountMenu);

    /**
     * 查询菜单
     */
    @RequestLine("POST /cgi-bin/get_current_selfmenu_info?access_token={accessToken}")
    MenuQuery.Result queryMenu(@Param("accessToken") String accessToken);

    /**
     * 微信网页授权-code换取access_token
     */
    @RequestLine("POST /sns/oauth2/access_token?appid={appId}&secret={secret}&code={code}&grant_type=authorization_code")
    OAWebPageAccessTokenQuery.Result gzhWebPageAccessToken(@Param("appId") String appId,
                                                           @Param("secret") String secret,
                                                           @Param("code") String code);

    /**
     * 微信网页授权-code换取access_token
     */
    @RequestLine("GET /sns/userinfo?access_token={accessToken}&openid={openId}&lang=zh_CN")
    OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(@Param("accessToken") String accessToken, @Param("openId") String openId);

}