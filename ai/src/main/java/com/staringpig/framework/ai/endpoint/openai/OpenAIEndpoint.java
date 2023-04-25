package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.Endpoint;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionResult;
import com.staringpig.framework.ai.endpoint.openai.api.ModerationRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ModerationResult;
import com.staringpig.framework.support.AllInOne;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

public class OpenAIEndpoint implements Endpoint {
    private final OpenAIAPI api;

    public OpenAIEndpoint(OpenAIAPI api) {
        this.api = api;
    }

    public void chat(ChatCompletionRequest request, Consumer<ChatCompletionResult> consumer) {
        api.createChatCompletion(request).enqueue(new OpenAICallback<>(consumer));
    }

    public void moderate(ModerationRequest request, Consumer<ModerationResult> consumer) {
        api.createModeration(request).enqueue(new OpenAICallback<>(consumer));
    }

    public static class OpenAICallback<T> implements Callback<T> {

        private final Consumer<T> consumer;

        public OpenAICallback(Consumer<T> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                consumer.accept(response.body());
            } else {
                OpenAiError error;
                try {
                    error = AllInOne.DEFAULT_OBJECT_MAPPER.readValue(
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

                    OpenAiError error = AllInOne.DEFAULT_OBJECT_MAPPER.readValue(errorBody, OpenAiError.class);
                    throw new OpenAiHttpException(error, e, e.code());
                } catch (IOException ex) {
                    // couldn't parse OpenAI error
                    throw e;
                }
            }
        }
    }
}
