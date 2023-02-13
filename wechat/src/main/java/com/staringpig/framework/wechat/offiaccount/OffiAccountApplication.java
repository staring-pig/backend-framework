package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.OPAppType;
import com.staringpig.framework.wechat.OPApplication;
import lombok.Getter;

/**
 * 公众号应用
 */
public abstract class OffiAccountApplication extends OPApplication {

    /**
     * 消息加解密密钥
     */
    @Getter
    private final String messageEncodingAESKey;
    /**
     * 消息通知地址
     */
    @Getter
    private final String messageNotifyUrl;

    protected OffiAccountApplication(String appId, String appSecret, String token, String messageEncodingAESKey,
                                     String messageNotifyUrl) {
        super(appId, appSecret, OPAppType.OFFI_ACCOUNT, token);
        this.messageEncodingAESKey = messageEncodingAESKey;
        this.messageNotifyUrl = messageNotifyUrl;
    }
}
