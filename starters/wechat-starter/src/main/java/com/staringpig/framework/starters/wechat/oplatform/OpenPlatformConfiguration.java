package com.staringpig.framework.starters.wechat.oplatform;

import com.staringpig.framework.starters.wechat.WechatProperties;
import com.staringpig.framework.wechat.oplatform.OpenPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OpenPlatformConfiguration {

    @Bean
    public OpenPlatform openPlatform(WechatProperties properties) {
        return new OpenPlatform(properties.getOpenPlatform().getAppId());
    }
}
