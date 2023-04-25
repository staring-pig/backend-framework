package com.staringpig.framework.starters.ai;

import com.staringpig.framework.ai.capability.ChatCompleting;
import com.staringpig.framework.ai.endpoint.openai.AuthenticationInterceptor;
import com.staringpig.framework.ai.endpoint.openai.Gpt_3_5_Turbo;
import com.staringpig.framework.ai.endpoint.openai.OpenAIAPI;
import com.staringpig.framework.ai.endpoint.openai.OpenAIEndpoint;
import com.staringpig.framework.ai.endpoint.openai.Text_Moderation_Latest;
import com.staringpig.framework.ai.endpoint.openai.utils.TokenizerAPI;
import com.staringpig.framework.ai.endpoint.openai.utils.UtilsEndpoint;
import com.staringpig.framework.ai.model.ChatContextStore;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.NoneCosting;
import com.staringpig.framework.ai.usage.TokensUsage;
import com.staringpig.framework.support.AllInOne;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@EnableCaching
@EnableConfigurationProperties(OpenAIProperties.class)
@Configuration(proxyBeanMethods = false)
public class OpenAIConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public JacksonConverterFactory jacksonConverterFactory() {
        return JacksonConverterFactory.create(AllInOne.DEFAULT_OBJECT_MAPPER);
    }

    @Bean
    @ConditionalOnMissingBean(value = Costing.class)
    public Costing<TokensUsage> tokensUsageCosting() {
        return (user, model1, usage) ->
                System.out.println("cost:" + user + model1.getName() + usage.toString());
    }

    @Bean
    @ConditionalOnBean(CacheManager.class)
    public ChatContextStore chatContextStore(CacheManager cacheManager) {
        return new CachedChatContextStore(cacheManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public ChatContextStore chatContextStore() {
        return new ChatContextStore() {

            final Map<String, ChatCompleting.ChatContext> memory = new ConcurrentHashMap<>();

            @Override
            public Optional<ChatCompleting.ChatContext> query(String chat) {
                return Optional.ofNullable(memory.get(chat));
            }

            @Override
            public void save(ChatCompleting.ChatContext context) {
                this.memory.put(context.getChat(), context);
            }
        };
    }

    @Bean
    public OkHttpClient openAIOkHttpClient(OpenAIProperties properties) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(properties.getToken()))
                .proxy(new Proxy(properties.getProxy().getType(),
                        new InetSocketAddress(properties.getProxy().getHostname(),
                                properties.getProxy().getPort())))
                .connectionPool(new ConnectionPool(
                        properties.getConnectionPool().getMaxIdleConnections(),
                        properties.getConnectionPool().getKeepAliveDuration(),
                        properties.getConnectionPool().getTimeUnit()))
                .readTimeout(properties.getReadTimeout())
                .connectTimeout(properties.getConnectTimeout())
                .build();
    }

    @Bean
    public OpenAIEndpoint openAIEndpoint(JacksonConverterFactory jacksonConverterFactory, OpenAIProperties properties,
                                         OkHttpClient openAIOkHttpClient) {
        return new OpenAIEndpoint(new Retrofit.Builder()
                .baseUrl(properties.getBaseUrl())
                .client(openAIOkHttpClient)
                .addConverterFactory(jacksonConverterFactory)
                .build().create(OpenAIAPI.class));
    }

    @Bean
    public Gpt_3_5_Turbo gpt_3_5_Turbo(JacksonConverterFactory jacksonConverterFactory, Costing<TokensUsage> costing,
                                       ChatContextStore chatContextStore, OpenAIProperties properties,
                                       OpenAIEndpoint openAIEndpoint) {
        return new Gpt_3_5_Turbo(costing, chatContextStore, openAIEndpoint,
                new UtilsEndpoint(new Retrofit.Builder()
                        .baseUrl(properties.getUtilsBaseUrl())
                        .addConverterFactory(jacksonConverterFactory)
                        .build().create(TokenizerAPI.class)));
    }

    @Bean
    public NoneCosting noneCosting() {
        return new NoneCosting();
    }

    @Bean
    public Text_Moderation_Latest textModerationLatest(OpenAIEndpoint openAIEndpoint, NoneCosting noneCosting) {
        return new Text_Moderation_Latest(openAIEndpoint, noneCosting);
    }
}
