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
import com.staringpig.framework.wechat.offiaccount.message.handler.event.ScanSubscribeEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.SubscribeEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.handler.event.UnsubscribeEventHandler;
import com.staringpig.framework.wechat.offiaccount.message.ordinary.VoiceMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import com.staringpig.framework.wechat.offiaccount.user.OAUserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Configuration(proxyBeanMethods = false)
@Import({OffiAccountConfiguration.RepositoryConfiguration.class,
        OffiAccountConfiguration.MessageHandlerConfiguration.class})
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

    @ConditionalOnMissingBean(value = OAUserRepository.class)
    @EnableJpaRepositories("com.staringpig.framework.starters.wechat.offiaccount")
    @Configuration(proxyBeanMethods = false)
    public static class RepositoryConfiguration {
    }

    @Bean
    public DefaultReply defaultReply() {
//        return new DefaultReply();
        return null;
    }

    @ConditionalOnBean(OAUserRepository.class)
    @ConditionalOnMissingBean(value = OAMessageHandler.class)
    public static class MessageHandlerConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public AllInOneHandler allInOneHandler() {
            return new AllInOneHandler() {
                @Override
                public Optional<ReplyMessage> onScanSubscribed(OAUser OAUser, String eventKey, String ticket) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> onScan(OAUser OAUser, String eventKey, String ticket) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> onSubscribed(OAUser OAUser) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> onUnSubscribed(OAUser OAUser) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> onUpLocation(OAUser oaUser, BigDecimal latitude, BigDecimal longitude, BigDecimal precision) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> onView(OAUser oaUser, String url) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveImage(OAUser oaUser, String picUrl, String mediaId) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveLink(OAUser oaUser, String title, String description, String url) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveLocation(OAUser oaUser, BigDecimal locationX, BigDecimal locationY, BigDecimal scale, String label) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveShortVideo(OAUser oaUser, String mediaId, String thumbMediaId) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveText(OAUser oaUser, String content) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveVideo(OAUser oaUser, String mediaId, String thumbMediaId) {
                    return Optional.empty();
                }

                @Override
                public Optional<ReplyMessage> receiveVoice(OAUser oaUser, String mediaId, VoiceMessage.Format format, String recognition) {
                    return Optional.empty();
                }
            };
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
