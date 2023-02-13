package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.api.miniprogram.GenerateUrlLinkCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.GetQrCodeCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.UrlLinkGenerateCommand;
import com.staringpig.framework.wechat.message.Message;

/**
 * 小程序应用服务
 */
public interface MiniProgramService {

    /**
     * 微信小程序登陆，为获取微信数据提供服务
     *
     * @param code 前端js编码
     * @return 加密后的
     */
    MiniProgramSession login(String code);

    String getPhoneInfo(String encryptedData, String sessionKey, String iv);

    /**
     * 发送消息
     *
     * @param message 消息
     * @param <T>     数据类型
     */
    <T> void sendMessage(Message<T> message);

    /**
     * 获取图片buffer
     */
    String getQrCodeLimited(GetQrCodeCommand<?> command);

    /**
     * 小程序跳转链接生成
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
     */
    String generateUrlLink(GenerateUrlLinkCommand command);

    /**
     * 生成URL Link
     */
    String generateUrlLink(UrlLinkGenerateCommand command);
}
