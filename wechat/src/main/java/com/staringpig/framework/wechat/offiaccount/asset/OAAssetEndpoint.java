package com.staringpig.framework.wechat.offiaccount.asset;

import com.staringpig.framework.wechat.Endpoint;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.ServerException;
import com.staringpig.framework.wechat.offiaccount.OffiAccountAPI;
import com.staringpig.framework.wechat.offiaccount.api.UploadVoiceCommand;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Endpoint
public class OAAssetEndpoint {

    private final OffiAccountAPI api;
    private final AccessTokenEndpoint accessTokenEndpoint;

    public OAAssetEndpoint(OffiAccountAPI api, AccessTokenEndpoint accessTokenEndpoint) {
        this.api = api;
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    public String uploadVoice(String appId, String appSecret, File media) {
        String accessToken = accessTokenEndpoint.accessToken(appId, appSecret);
        UploadVoiceCommand.Result result;
        try {
            result = api.uploadVoice(accessToken, media).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
        return result.getMediaId();
    }
}
