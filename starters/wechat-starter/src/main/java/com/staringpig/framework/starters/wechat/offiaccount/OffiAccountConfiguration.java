package com.staringpig.framework.starters.wechat.offiaccount;

import com.staringpig.framework.starters.wechat.WechatProperties;
import com.staringpig.framework.wechat.accesstoken.AccessTokenEndpoint;
import com.staringpig.framework.wechat.offiaccount.OAEndpoint;
import com.staringpig.framework.wechat.offiaccount.OffiAccount;
import com.staringpig.framework.wechat.offiaccount.OffiAccountAPI;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;
import com.staringpig.framework.wechat.offiaccount.message.handler.AllInOneHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.DefaultReply;
import com.staringpig.framework.wechat.offiaccount.message.handler.ImageMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.LinkMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.LocationMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.ShortVideoMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.TextMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.VideoMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.VoiceMessageHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.ScanEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.ScanSubscribeEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.SubscribeEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.UnsubscribeEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.UpLocationEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.ViewEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.click.KeyClickEventHandler;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Collection;

@Configuration(proxyBeanMethods = false)
@Import({OffiAccountConfiguration.RepositoryConfiguration.class,
        OffiAccountConfiguration.MessageHandlerConfiguration.class})
public class OffiAccountConfiguration {

    @Bean
    public OAEndpoint oaEndpoint(AccessTokenEndpoint accessTokenEndpoint,
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

    @ConditionalOnMissingBean(value = OAUserRepository.class)
    @EntityScan("com.staringpig.framework.starters.wechat.offiaccount")
    @EnableJpaRepositories("com.staringpig.framework.starters.wechat.offiaccount")
    @Configuration(proxyBeanMethods = false)
    public static class RepositoryConfiguration {
    }

    @Bean
    @ConditionalOnProperty(prefix = "staring-pig.framework.wechat.offi-account", name = "default-replay")
    public DefaultReply defaultReply(WechatProperties properties) {
        return new DefaultReply(properties.getOffiAccount().getDefaultReplay().getContent(),
                properties.getOffiAccount().getDefaultReplay().getWelcomeContent());
    }

    @ConditionalOnBean(OAUserRepository.class)
    @ConditionalOnMissingBean(value = OAMessageHandler.class)
    public static class MessageHandlerConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public AllInOneHandler allInOneHandler(DefaultReply defaultReply) {
            return new DefaultAllInOneHandler(defaultReply);
        }

        @Bean
        public ScanEventHandler scanEventHandler(AllInOneHandler normalEventHandler) {
            return new ScanEventHandler(normalEventHandler);
        }

        @Bean
        public ScanSubscribeEventHandler scanSubscribeEventHandler(OAUserRepository oaUserRepository,
                                                                   AllInOneHandler normalEventHandler) {
            return new ScanSubscribeEventHandler(oaUserRepository, normalEventHandler);
        }

        @Bean
        public SubscribeEventHandler subscribeEventHandler(OAUserRepository oaUserRepository,
                                                           AllInOneHandler normalEventHandler) {
            return new SubscribeEventHandler(oaUserRepository, normalEventHandler);
        }

        @Bean
        public UnsubscribeEventHandler unsubscribeEventHandler(OAUserRepository oaUserRepository,
                                                               AllInOneHandler normalEventHandler) {
            return new UnsubscribeEventHandler(oaUserRepository, normalEventHandler);
        }

        @Bean
        public UpLocationEventHandler upLocationEventHandler(AllInOneHandler normalEventHandler) {
            return new UpLocationEventHandler(normalEventHandler);
        }

        @Bean
        public ViewEventHandler viewEventHandler(AllInOneHandler normalEventHandler) {
            return new ViewEventHandler(normalEventHandler);
        }

        @Bean
        public KeyClickEventHandler keyClickEventHandler(Collection<KeyClickEventHandler.SpecialClick> specialClicks,
                                                         DefaultReply defaultReply) {
            return new KeyClickEventHandler(specialClicks, defaultReply);
        }

        @Bean
        public KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter(
                Collection<KeyClickEventHandler.SpecialClick> specialClicks) {
            return new KeyClickEventHandler.EnumsKeyConverter(specialClicks);
        }

        @Bean
        public ImageMessageHandler imageMessageHandler(AllInOneHandler normalEventHandler) {
            return new ImageMessageHandler(normalEventHandler);
        }

        @Bean
        public LinkMessageHandler linkMessageHandler(AllInOneHandler normalEventHandler) {
            return new LinkMessageHandler(normalEventHandler);
        }

        @Bean
        public LocationMessageHandler locationMessageHandler(AllInOneHandler normalEventHandler) {
            return new LocationMessageHandler(normalEventHandler);
        }

        @Bean
        public ShortVideoMessageHandler shortVideoMessageHandler(AllInOneHandler normalEventHandler) {
            return new ShortVideoMessageHandler(normalEventHandler);
        }

        @Bean
        public TextMessageHandler textMessageHandler(AllInOneHandler normalEventHandler) {
            return new TextMessageHandler(normalEventHandler);
        }

        @Bean
        public VideoMessageHandler videoMessageHandler(AllInOneHandler normalEventHandler) {
            return new VideoMessageHandler(normalEventHandler);
        }

        @Bean
        public VoiceMessageHandler voiceMessageHandler(AllInOneHandler normalEventHandler) {
            return new VoiceMessageHandler(normalEventHandler);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> nothingKeyConverter() {
        return (KeyClickEvent.KeyConverter<KeyClickEvent.Key>) keyString -> null;
    }

    @Bean
    public OffiAccountReceiver offiAccountReceiver(OffiAccount offiAccount,
                                                   Collection<OAMessageHandler<? extends OAMessage>> messageHandlers,
                                                   OAUserRepository oaUserRepository,
                                                   KeyClickEvent.KeyConverter<? extends KeyClickEvent.Key> keyConverter
    ) {
        return new OffiAccountReceiver(offiAccount, messageHandlers, oaUserRepository, keyConverter);
    }
}
