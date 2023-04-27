package com.staringpig.framework.starters.wechat;

import com.staringpig.framework.starters.AutoConfiguration;
import com.staringpig.framework.starters.wechat.accesstoken.AccessTokenConfiguration;
import com.staringpig.framework.starters.wechat.offiaccount.OffiAccountConfiguration;
import com.staringpig.framework.starters.wechat.oplatform.OpenPlatformConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@EnableConfigurationProperties(WechatProperties.class)
@Configuration(proxyBeanMethods = false)
@Import({AccessTokenConfiguration.class, OffiAccountConfiguration.class, OpenPlatformConfiguration.class})
@AutoConfigureAfter(AutoConfiguration.class)
public class WechatConfiguration {

}
