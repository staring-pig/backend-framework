package com.staringpig.framework.starters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staringpig.framework.support.AllInOne;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration(proxyBeanMethods = false)
public class AutoConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return AllInOne.DEFAULT_OBJECT_MAPPER;
    }

    @Bean
    public JacksonConverterFactory jacksonConverterFactory(ObjectMapper objectMapper) {
        return JacksonConverterFactory.create(objectMapper);
    }

    @Bean
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Bean
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new okhttp3.OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }
}
