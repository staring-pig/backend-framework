package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.api.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.offiaccount.api.OAWebPageJsTicketCommand;
import com.staringpig.framework.wechat.offiaccount.api.SendCustomMessageCommand;
import com.staringpig.framework.wechat.offiaccount.api.SendCustomVoiceMessageCommand;
import com.staringpig.framework.wechat.offiaccount.api.UploadVoiceCommand;
import com.staringpig.framework.wechat.offiaccount.api.UserInfoQuery;
import feign.Param;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.io.File;

public interface OffiAccountAPI {

    String URL = "https://api.weixin.qq.com";

    /**
     * 微信网页授权access_token获取js-ticket
     */
    @POST("/cgi-bin/ticket/getticket?type=jsapi")
    Call<OAWebPageJsTicketCommand.Result> oaWebTicket(@Param("accessToken") String accessToken);

    /**
     * 获取用户信息
     */
    @POST("/cgi-bin/user/info?lang=zh_CN")
    Call<UserInfoQuery.Result> getUserInfo(@Param("accessToken") String accessToken, @Param("openid") String openId);

    /**
     * 微信网页授权-code换取access_token
     */
    @POST("/sns/oauth2/access_token?grant_type=authorization_code")
    Call<OAWebPageAccessCommand.Result> oaWebAccessToken(@Param("appId") String appId, @Param("secret") String secret,
                                                         @Param("code") String code);

    /**
     * 客服接口 - 发消息
     */
    @POST("/cgi-bin/message/custom/send")
    Call<SendCustomMessageCommand.Result> sendCustomMessage(@Param("accessToken") String accessToken,
                                                            SendCustomMessageCommand command);

    /**
     * 客服接口 - 发语音消息
     */
    @POST("/cgi-bin/message/custom/send")
    Call<SendCustomVoiceMessageCommand.Result> sendCustomVoiceMessage(@Param("accessToken") String accessToken,
                                                                      SendCustomVoiceMessageCommand command);

    /**
     * 上传语音
     */
    @POST("/cgi-bin/media/upload?type=voice")
    @Headers("Content-Type: multipart/form-data")
    Call<UploadVoiceCommand.Result> uploadVoice(@Param("accessToken") String accessToken, @Param("media") File media);
}