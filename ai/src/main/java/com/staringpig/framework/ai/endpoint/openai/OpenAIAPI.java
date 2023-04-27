package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionResult;
import com.staringpig.framework.ai.endpoint.openai.api.ModerationRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ModerationResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface OpenAIAPI {

    @POST("v1/chat/completions")
    Call<ChatCompletionResult> createChatCompletion(@Body ChatCompletionRequest request);

    @POST("v1/moderations")
    Call<ModerationResult> createModeration(@Body ModerationRequest request);
}
