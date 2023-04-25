package com.staringpig.framework.starters.wechat.offiaccount;

import com.staringpig.framework.starters.wechat.WechatProperties;
import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.offiaccount.OAEndpoint;
import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.offiaccount.OffiAccountAPI;
import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.account.OAAccountRepository;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Collection;

@Configuration(proxyBeanMethods = false)
public class OffiAccountConfiguration {

    @Bean
    public OAEndpoint endpoint(AccessTokenEndpoint accessTokenEndpoint,
                               JacksonConverterFactory jacksonConverterFactory) {
        return new OAEndpoint(new Retrofit.Builder()
                .baseUrl(OffiAccountAPI.URL)
                .addConverterFactory(jacksonConverterFactory)
                .build().create(OffiAccountAPI.class), accessTokenEndpoint);
    }

    @Bean
    public OffiAccount offiAccount(WechatProperties properties, OAEndpoint endpoint) {
        return new OffiAccount(properties.getOffiAccount().getAppId(), properties.getOffiAccount().getAppSecret(),
                properties.getOffiAccount().getToken(), endpoint);
    }

    @ConditionalOnMissingBean(value = OAAccountRepository.class)
    @EnableJpaRepositories("com.staringpig.framework.starters.wechat.offiaccount")
    @Configuration(proxyBeanMethods = false)
    public static class RepositoryConfiguration {
    }

    @Bean
    public OffiAccountReceiver offiAccountReceiver(OffiAccount offiAccount,
                                                   Collection<OAMessageHandler<? extends OAMessage>> messageHandlers,
                                                   OAAccountRepository<? extends OAAccount> oaAccountRepository,
                                                   KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter
    ) {
        return new OffiAccountReceiver(offiAccount, messageHandlers, oaAccountRepository, keyConverter);
    }
}
