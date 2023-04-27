package com.staringpig.framework.ai.endpoint.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.staringpig.framework.ai.endpoint.Endpoint;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionResult;
import com.staringpig.framework.ai.endpoint.openai.api.ModerationRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ModerationResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class OpenAIEndpoint implements Endpoint {
    private final OpenAIAPI api;
    private final ObjectMapper objectMapper;

    public OpenAIEndpoint(OpenAIAPI api, ObjectMapper objectMapper) {
        this.api = api;
        this.objectMapper = objectMapper;
    }

    public void chat(ChatCompletionRequest request, Consumer<ChatCompletionResult> consumer) {
        api.createChatCompletion(request).enqueue(new OpenAICallback<>(consumer, this.objectMapper));
    }

    public void moderate(ModerationRequest request, Consumer<ModerationResult> consumer) {
        api.createModeration(request).enqueue(new OpenAICallback<>(consumer, this.objectMapper));
    }

    public static class OpenAICallback<T> implements Callback<T> {

        private final Consumer<T> consumer;
        private final ObjectMapper objectMapper;

        public OpenAICallback(Consumer<T> consumer, ObjectMapper objectMapper) {
            this.consumer = consumer;
            this.objectMapper = objectMapper;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                consumer.accept(response.body());
            } else {
                OpenAiError error;
                try {
                    error = objectMapper.readValue(
                            Objects.requireNonNull(response.errorBody()).string(), OpenAiError.class);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                throw new OpenAiHttpException(error, null, response.code());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            if (t instanceof HttpException) {
                HttpException e = (HttpException) t;
                try {
                    if (e.response() == null || e.response().errorBody() == null) {
                        throw e;
                    }
                    String errorBody = e.response().errorBody().string();

                    OpenAiError error = objectMapper.readValue(errorBody, OpenAiError.class);
                    throw new OpenAiHttpException(error, e, e.code());
                } catch (IOException ex) {
                    // couldn't parse OpenAI error
                    throw e;
                }
            }
        }
    }
}
