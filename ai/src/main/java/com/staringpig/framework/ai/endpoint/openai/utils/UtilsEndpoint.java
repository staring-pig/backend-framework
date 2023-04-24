package com.staringpig.framework.ai.endpoint.openai.utils;

import com.staringpig.framework.ai.endpoint.Endpoint;
import com.staringpig.framework.ai.endpoint.openai.api.ChatMessage;
import com.staringpig.framework.ai.endpoint.openai.api.CountTokensCommand;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsEndpoint implements Endpoint {

    private final TokenizerAPI tokenizerAPI;

    public UtilsEndpoint(TokenizerAPI tokenizerAPI) {
        this.tokenizerAPI = tokenizerAPI;
    }

    public Long countTokens(String model, List<ChatMessage> chatMessages) {
        try {
            return tokenizerAPI.countTokens(CountTokensCommand.builder()
                    .model(model)
                    .content(chatMessages.stream()
                            .map(msg -> CountTokensCommand.ChatMessage.builder()
                                    .name(msg.getName())
                                    .role(msg.getRole())
                                    .content(msg.getContent())
                                    .build())
                            .collect(Collectors.toList()))
                    .build()).execute().body();
        } catch (IOException e) {
            throw new RuntimeException("");
        }
    }
}
