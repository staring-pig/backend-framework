package com.staringpig.framework.ai.endpoint.openai.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.staringpig.framework.ai.endpoint.Endpoint;
import com.staringpig.framework.ai.endpoint.openai.api.ChatMessage;

import java.io.IOException;
import java.util.List;

public class UtilsEndpoint implements Endpoint {

    private final TokenizerAPI tokenizerAPI;

    public UtilsEndpoint(TokenizerAPI tokenizerAPI) {
        this.tokenizerAPI = tokenizerAPI;
    }

    public Long countTokens(List<ChatMessage> chatMessages) {
        try {
            return tokenizerAPI.countTokens(defaultObjectMapper().writeValueAsString(chatMessages)).execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper defaultObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return mapper;
    }
}
