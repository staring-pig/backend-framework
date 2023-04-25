package com.staringpig.framework.starters.wechat;

import com.staringpig.framework.starters.wechat.accesstoken.AccessTokenConfiguration;
import com.staringpig.framework.starters.wechat.offiaccount.OffiAccountConfiguration;
import com.staringpig.framework.support.AllInOne;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import retrofit2.converter.jackson.JacksonConverterFactory;

@EnableConfigurationProperties(WechatProperties.class)
@Configuration(proxyBeanMethods = false)
@Import({AccessTokenConfiguration.class, OffiAccountConfiguration.class})
public class WechatConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JacksonConverterFactory jacksonConverterFactory() {
        return JacksonConverterFactory.create(AllInOne.DEFAULT_OBJECT_MAPPER);
    }
}
