package com.staringpig.framework.wechat.accesstoken;

import com.staringpig.framework.wechat.accesstoken.api.AccessTokenQuery;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AccessTokenAPI {

    /**
     * 获取accessToken
     */
    @POST("cgi-bin/token?grant_type=client_credential")
    Call<AccessTokenQuery.Result> getAccessToken(@Query("appid") String appId, @Query("secret") String appSecret);
}
