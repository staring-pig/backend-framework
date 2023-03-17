package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageAccessCommand;
import com.staringpig.framework.wechat.client.api.offi.webpage.OAWebPageUserInfoQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ldh
 * time 2022-4-14 16:09
 */
public interface OAWebPageService {

    OAWebPageAccessCommand.Result webPageAccess(String code);

    String fetchOpenId(String code);

    OAWebPageUserInfoQuery.Result gzhWebPageUserInfo(String webAccessToken, String openId);

    /**
     * js-sdk-sign
     */
    JsSDKSign jsSDKSign(String url);

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class JsSDKSign {
        /**
         * 必填，生成签名的时间戳
         */
        private String timestamp;
        /**
         * 必填，生成签名的随机串
         */
        private String nonceStr;
        /**
         * 必填，签名
         */
        private String signature;
    }

}
