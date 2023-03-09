package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.OffiAccountClient;
import com.staringpig.framework.wechat.client.WechatAccessTokenClient;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.offi.MenuAdd;
import com.staringpig.framework.wechat.client.api.offi.MenuQuery;
import com.staringpig.framework.wechat.client.api.offi.SendCustomMessageCommand;
import com.staringpig.framework.wechat.client.api.offi.SendCustomVoiceMessageCommand;
import com.staringpig.framework.wechat.client.api.offi.SendTemplateMessageCommand;
import com.staringpig.framework.wechat.client.api.offi.UploadVoiceCommand;
import com.staringpig.framework.wechat.client.api.offi.UserInfoQuery;
import com.staringpig.framework.wechat.message.Message;
import com.staringpig.framework.wechat.message.OffiAccountTemplatedMessage;
import com.staringpig.framework.wechat.message.OffiAccountToMiniProgramTemplatedMessage;
import com.staringpig.framework.wechat.message.OffiAccountToUrlTemplatedMessage;
import com.staringpig.framework.wechat.offiaccount.menu.OffiAccountMenu;

import java.io.File;

/**
 * 基础的开放平台服务，封装一些权限验证
 *
 * @author niumy
 */
public class RemoteOffiAccountService implements OffiAccountService {

    private final OPApplication opApplication;
    private final OffiAccountClient offiAccountClient;
    private final WechatAccessTokenClient wechatAccessTokenClient;

    public RemoteOffiAccountService(OPApplication opApplication, OffiAccountClient offiAccountClient,
                                    WechatAccessTokenClient wechatAccessTokenClient) {
        this.opApplication = opApplication;
        this.offiAccountClient = offiAccountClient;
        this.wechatAccessTokenClient = wechatAccessTokenClient;
    }

    /**
     * 获取accessToken
     */
    private synchronized String fetchAccessToken() {
        AccessTokenQuery.Result result =
                wechatAccessTokenClient.getAccessToken(opApplication.getAppId(), opApplication.getAppSecret());
        result.isOK();
        return result.getAccessToken();
    }

    @Override
    public UserInfoQuery.Result getUserInfo(String openId) {
        String accessToken = this.fetchAccessToken();
        UserInfoQuery.Result userInfo = offiAccountClient.getUserInfo(accessToken, openId);
        userInfo.isOK();
        return userInfo;
    }

    @Override
    public <T> void sendTemplateMessage(Message<T> message) {
        if (message instanceof OffiAccountTemplatedMessage) {
            String accessToken = this.fetchAccessToken();

            if (message instanceof OffiAccountToUrlTemplatedMessage) {
                OffiAccountToUrlTemplatedMessage<T> toUrlTemplatedMessage = (OffiAccountToUrlTemplatedMessage<T>) message;

                WechatServerResult result = offiAccountClient.sendTemplateMessage(accessToken,
                        SendTemplateMessageCommand.builder()
                                .data(toUrlTemplatedMessage.getData())
                                .openId(toUrlTemplatedMessage.getOpenId())
                                .templateId(toUrlTemplatedMessage.getTemplateId())
                                .color(toUrlTemplatedMessage.getColor())
                                .url(toUrlTemplatedMessage.getUrl())
                                .build());
                result.isOK();
                return;
            }

            if (message instanceof OffiAccountToMiniProgramTemplatedMessage) {
                OffiAccountToMiniProgramTemplatedMessage<T> toMiniProgramTemplatedMessage =
                        (OffiAccountToMiniProgramTemplatedMessage<T>) message;

                WechatServerResult result = offiAccountClient.sendTemplateMessage(accessToken,
                        SendTemplateMessageCommand.builder()
                                .data(toMiniProgramTemplatedMessage.getData())
                                .openId(toMiniProgramTemplatedMessage.getOpenId())
                                .templateId(toMiniProgramTemplatedMessage.getTemplateId())
                                .color(toMiniProgramTemplatedMessage.getColor())
                                .url(toMiniProgramTemplatedMessage.getUrl())
                                .miniProgram(new SendTemplateMessageCommand.MiniProgram(
                                        toMiniProgramTemplatedMessage.getMiniProgram().getAppId(),
                                        toMiniProgramTemplatedMessage.getPagePath()))
                                .build());
                result.isOK();
                return;
            }

            OffiAccountTemplatedMessage<T> templatedMessage = (OffiAccountTemplatedMessage<T>) message;

            WechatServerResult result = offiAccountClient.sendTemplateMessage(accessToken,
                    SendTemplateMessageCommand.builder()
                            .data(templatedMessage.getData())
                            .openId(templatedMessage.getOpenId())
                            .templateId(templatedMessage.getTemplateId())
                            .build());
            result.isOK();
        }
    }

    @Override
    public void createMenu(OffiAccountMenu offiAccountMenu) {
        String accessToken = this.fetchAccessToken();
        MenuAdd.Result createMenuResult = offiAccountClient.createMenu(accessToken, offiAccountMenu);
        createMenuResult.isOK();
    }

    @Override
    public OffiAccountMenu queryMenu() {
        String accessToken = this.fetchAccessToken();
        MenuQuery.Result queryResult = offiAccountClient.queryMenu(accessToken);
        queryResult.isOK();
        return queryResult.getOffiAccountMenu();
    }

    @Override
    public void sendCustomMessageBySpecialist(String openId, String textContent) {
        String accessToken = this.fetchAccessToken();
        SendCustomMessageCommand.Result result = offiAccountClient.sendCustomMessageBySpecialist(accessToken,
                SendCustomMessageCommand.builder()
                        .messageType("text")
                        .text(new SendCustomMessageCommand.Text(textContent))
                        .openId(openId)
                        .build());
        result.isOK();
    }

    @Override
    public void sendCustomVoiceMessageBySpecialist(String openId, String mediaId) {
        String accessToken = this.fetchAccessToken();
        SendCustomVoiceMessageCommand.Result result = offiAccountClient.sendCustomVoiceMessageBySpecialist(accessToken,
                SendCustomVoiceMessageCommand.builder()
                        .messageType("voice")
                        .voice(new SendCustomVoiceMessageCommand.Voice(mediaId))
                        .openId(openId)
                        .build());
        result.isOK();
    }

    @Override
    public String uploadVoice(File media) {
        UploadVoiceCommand.Result result = offiAccountClient.uploadVoice(this.fetchAccessToken(), media);
        result.isOK();
        return result.getMediaId();
    }
}
