package com.staringpig.framework.wechat.accesstoken;

import com.staringpig.framework.wechat.Endpoint;
import com.staringpig.framework.wechat.accesstoken.api.AccessTokenQuery;
import com.staringpig.framework.wechat.ServerException;

import java.io.IOException;
import java.util.Objects;

@Endpoint
public class AccessTokenEndpoint {

    private final AccessTokenAPI api;

    public AccessTokenEndpoint(AccessTokenAPI api) {
        this.api = api;
    }

    public synchronized String accessToken(String appId, String appSecret) {
        AccessTokenQuery.Result result;
        try {
            result = api.getAccessToken(appId, appSecret).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
        return result.getAccessToken();
    }
}
