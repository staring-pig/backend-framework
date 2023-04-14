package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.Endpoint;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionResult;
import retrofit2.Callback;

public class OpenAIEndpoint implements Endpoint {
    private final OpenAIAPI api;

    public OpenAIEndpoint(OpenAIAPI api) {
        this.api = api;
    }

    public void chat(ChatCompletionRequest request, Callback<ChatCompletionResult> callback) {
        api.createChatCompletion(request).enqueue(callback);
    }
}
