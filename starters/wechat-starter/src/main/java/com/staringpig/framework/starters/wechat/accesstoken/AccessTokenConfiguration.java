package com.staringpig.framework.starters.wechat.accesstoken;

import com.staringpig.framework.starters.wechat.WechatProperties;
import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.accesstoken.AccessTokenAPI;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.offiaccount.OffiAccountAPI;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration(proxyBeanMethods = false)
public class AccessTokenConfiguration {

    @Bean
    public AccessTokenEndpoint accessTokenEndpoint(WechatProperties properties,
                                                   JacksonConverterFactory jacksonConverterFactory,
                                                   OkHttpClient okHttpClient) {
        return new AccessTokenEndpoint(new Retrofit.Builder()
                .baseUrl(AllInOne.isEmpty(properties.getAccessTokenBaseUrl()) ? OffiAccountAPI.URL
                        : properties.getAccessTokenBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(jacksonConverterFactory)
                .build().create(AccessTokenAPI.class));
    }
}
