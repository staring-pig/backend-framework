package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.offiaccount.api.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.offiaccount.api.OAWebPageJsTicketCommand;
import com.staringpig.framework.wechat.offiaccount.api.SendCustomMessageCommand;
import com.staringpig.framework.wechat.offiaccount.api.SendCustomVoiceMessageCommand;
import com.staringpig.framework.wechat.offiaccount.api.UploadVoiceCommand;
import com.staringpig.framework.wechat.offiaccount.api.UserInfoQuery;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.File;

public interface OffiAccountAPI {

    String URL = "https://api.weixin.qq.com";

    /**
     * 微信网页授权access_token获取js-ticket
     */
    @POST("/cgi-bin/ticket/getticket?type=jsapi")
    Call<OAWebPageJsTicketCommand.Result> oaWebTicket(@Query("accessToken") String accessToken);

    /**
     * 获取用户信息
     */
    @POST("/cgi-bin/user/info?lang=zh_CN")
    Call<UserInfoQuery.Result> getUserInfo(@Query("accessToken") String accessToken, @Query("openid") String openId);

    /**
     * 微信网页授权-code换取access_token
     */
    @POST("/sns/oauth2/access_token?grant_type=authorization_code")
    Call<OAWebPageAccessCommand.Result> oaWebAccessToken(@Query("appId") String appId, @Query("secret") String secret,
                                                         @Query("code") String code);

    /**
     * 客服接口 - 发消息
     */
    @POST("/cgi-bin/message/custom/send")
    Call<SendCustomMessageCommand.Result> sendCustomMessage(@Query("accessToken") String accessToken,
                                                            @Body SendCustomMessageCommand command);

    /**
     * 客服接口 - 发语音消息
     */
    @POST("/cgi-bin/message/custom/send")
    Call<SendCustomVoiceMessageCommand.Result> sendCustomVoiceMessage(@Query("accessToken") String accessToken,
                                                                      @Body SendCustomVoiceMessageCommand command);

    /**
     * 上传语音
     */
    @POST("/cgi-bin/media/upload?type=voice")
    @Headers("Content-Type: multipart/form-data")
    Call<UploadVoiceCommand.Result> uploadVoice(@Query("accessToken") String accessToken, @Query("media") File media);
}