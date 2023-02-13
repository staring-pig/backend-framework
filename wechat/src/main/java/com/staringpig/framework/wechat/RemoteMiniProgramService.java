package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.MiniProgramClient;
import com.staringpig.framework.wechat.client.WechatAccessTokenClient;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.*;
import com.staringpig.framework.wechat.client.api.miniprogram.Code2SessionQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.GenerateUrlLinkCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.GetQrCodeCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.PhoneInfoQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.SendSubscribedMessageCommand;
import com.staringpig.framework.wechat.client.WechatServerException;
import com.staringpig.framework.wechat.message.Message;
import com.staringpig.framework.wechat.message.MiniProgramSubscribedMessage;
import com.staringpig.framework.wechat.utils.WxSignUtil;

/**
 * 基础的开放平台服务，封装一些权限验证
 *
 * @author niumy
 */
public class RemoteMiniProgramService implements MiniProgramService {

    private final OPApplication opApplication;
    private final MiniProgramClient miniProgramClient;
    private final WechatAccessTokenClient wechatAccessTokenClient;

    public RemoteMiniProgramService(OPApplication opApplication, MiniProgramClient miniProgramClient,
                                    WechatAccessTokenClient wechatAccessTokenClient) {
        this.opApplication = opApplication;
        this.miniProgramClient = miniProgramClient;
        this.wechatAccessTokenClient = wechatAccessTokenClient;
    }

    /**
     * 获取accessToken
     */
    private String fetchAccessToken() {
        AccessTokenQuery.Result result =
                wechatAccessTokenClient.getAccessToken(opApplication.getAppId(), opApplication.getAppSecret());
        result.isOK();
        return result.getAccessToken();
    }

    @Override
    public MiniProgramSession login(String code) {
        Code2SessionQuery.Result result =
                miniProgramClient.code2Session(this.opApplication.getAppId(), this.opApplication.getAppSecret(), code);

        result.isOK();

        return new MiniProgramSession(result.getOpenId(), this.opApplication, result.getUnionId(),
                result.getSessionKey());
    }

    @Override
    public String getPhoneInfo(String encryptedData, String sessionKey, String iv) throws WechatServerException {
        PhoneInfoQuery.Result result = WxSignUtil.decryptAESPhoneInfo(PhoneInfoQuery.builder()
                .encryptedData(encryptedData)
                .sessionKey(sessionKey)
                .iv(iv)
                .build());
        return result.getPhoneNumber();
    }

    @Override
    public <T> void sendMessage(Message<T> message) {
        if (message instanceof MiniProgramSubscribedMessage) {
            String accessToken = this.fetchAccessToken();

            MiniProgramSubscribedMessage<T> subscribedMessage = (MiniProgramSubscribedMessage<T>) message;

            WechatServerResult result = miniProgramClient.sendMessage(accessToken,
                    SendSubscribedMessageCommand.builder()
                            .miniProgramState(subscribedMessage.getMiniProgramState())
                            .data(subscribedMessage.getData())
                            .lang(subscribedMessage.getLang())
                            .openId(subscribedMessage.getOpenId())
                            .page(subscribedMessage.getPage())
                            .templateId(subscribedMessage.getTemplateId())
                            .build());
            result.isOK();
        }
    }

    @Override
    public String getQrCodeLimited(GetQrCodeCommand<?> command) {
        String accessToken = this.fetchAccessToken();
        GetQrCodeCommand.Result result = miniProgramClient.getQrCodeLimited(accessToken, command);
        result.isOK();
        return result.getBuffer();
    }

    @Override
    public String generateUrlLink(UrlLinkGenerateCommand command) {
        String accessToken = this.fetchAccessToken();
        UrlLinkGenerateCommand.Result result = miniProgramClient.generateUrlLink(accessToken, command);
        result.isOK();
        return result.getUrlLink();
    }

    @Override
    public String generateUrlLink(GenerateUrlLinkCommand command) {
        String accessToken = this.fetchAccessToken();
        GenerateUrlLinkCommand.Result result = miniProgramClient.generateUrlLink(accessToken, command);
        result.isOK();
        return result.getUrlLink();
    }

}
