package com.staringpig.framework.wechat.miniprogram;

import com.staringpig.framework.wechat.miniprogram.api.Code2SessionQuery;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 小程序客户端
 */
public interface MiniProgramAPI {

    String URL = "https://api.weixin.qq.com/";

    /**
     * jscode 转 session
     *
     * @return 结果
     */
    @GET("sns/jscode2session?grant_type=authorization_code")
    Call<Code2SessionQuery.Result> code2Session(@Query("appid") String appId, @Query("secret") String appSecret,
                                                @Query("js_code") String code);
}
