package com.staringpig.framework.starters.wechat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "staring-pig.framework.wechat")
public class WechatProperties {

    /**
     * access-token 管理地址
     */
    private String accessTokenBaseUrl;

    @NestedConfigurationProperty
    private OpenPlatform openPlatform;

    @NestedConfigurationProperty
    private OffiAccount offiAccount;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OffiAccount {
        /**
         * app id
         */
        private String appId;
        /**
         * appSecret
         */
        private String appSecret;
        /**
         * 令牌
         */
        private String token;
        /**
         * 通知地址
         */
        private String notifyPath;
        /**
         * 默认回复
         */
        @NestedConfigurationProperty
        private DefaultReplay defaultReplay;

        @Setter
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class DefaultReplay {
            private Boolean open = true;
            /**
             * 默认内容
             */
            private String content;
            /**
             * 环境内容
             */
            private String welcomeContent;
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OpenPlatform {
        /**
         * 开放平台也有自己的ID
         */
        @Getter
        private String appId;
    }
}
