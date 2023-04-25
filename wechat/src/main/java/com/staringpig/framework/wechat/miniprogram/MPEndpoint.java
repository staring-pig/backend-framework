package com.staringpig.framework.wechat.miniprogram;

import com.staringpig.framework.wechat.Endpoint;
import com.staringpig.framework.wechat.ServerException;
import com.staringpig.framework.wechat.miniprogram.api.Code2SessionQuery;

import java.io.IOException;
import java.util.Objects;

@Endpoint
public class MPEndpoint {

    private final MiniProgramAPI api;

    public MPEndpoint(MiniProgramAPI api) {
        this.api = api;
    }

    public MiniProgram.Session login(String appId, String appSecret, String code) {
        Code2SessionQuery.Result result;
        try {
            result = api.code2Session(appId, appSecret, code).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
        return new MiniProgram.Session(result.getOpenId(), result.getUnionId(), result.getSessionKey());
    }
}
