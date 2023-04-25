package com.staringpig.framework.wechat.offiaccount.cservice;

import com.staringpig.framework.wechat.Endpoint;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.ServerException;
import com.staringpig.framework.wechat.offiaccount.OffiAccountAPI;
import com.staringpig.framework.wechat.offiaccount.api.SendCustomMessageCommand;
import com.staringpig.framework.wechat.offiaccount.api.SendCustomVoiceMessageCommand;

import java.io.IOException;
import java.util.Objects;

@Endpoint
public class OACServiceEndpoint {

    private final OffiAccountAPI api;
    private final AccessTokenEndpoint accessTokenEndpoint;

    public OACServiceEndpoint(OffiAccountAPI api, AccessTokenEndpoint accessTokenEndpoint) {
        this.api = api;
        this.accessTokenEndpoint = accessTokenEndpoint;
    }

    public void sendCustomMessage(String appId, String appSecret, String openId, String textContent) {
        String accessToken = accessTokenEndpoint.accessToken(appId, appSecret);
        SendCustomMessageCommand.Result result;
        try {
            result = api.sendCustomMessage(accessToken,
                    SendCustomMessageCommand.builder()
                            .messageType("text")
                            .text(new SendCustomMessageCommand.Text(textContent))
                            .openId(openId)
                            .build()).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
    }

    public void sendCustomVoiceMessage(String appId, String appSecret, String openId, String mediaId) {
        String accessToken = accessTokenEndpoint.accessToken(appId, appSecret);
        SendCustomVoiceMessageCommand.Result result = null;
        try {
            result = api.sendCustomVoiceMessage(accessToken,
                    SendCustomVoiceMessageCommand.builder()
                            .messageType("voice")
                            .voice(new SendCustomVoiceMessageCommand.Voice(mediaId))
                            .openId(openId)
                            .build()).execute().body();
        } catch (IOException e) {
            throw new ServerException(e.getMessage());
        }
        Objects.requireNonNull(result).isOK();
    }
}
