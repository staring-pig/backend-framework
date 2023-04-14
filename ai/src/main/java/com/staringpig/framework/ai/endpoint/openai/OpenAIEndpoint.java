package com.staringpig.framework.ai.endpoint.openai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.staringpig.framework.ai.endpoint.Endpoint;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionResult;
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

    public static class OpenAICallback<T> implements Callback<T> {

        private final Consumer<T> consumer;
        protected static final ObjectMapper errorMapper = defaultObjectMapper();

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
                    error = errorMapper.readValue(Objects.requireNonNull(response.errorBody()).string(),
                            OpenAiError.class);
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

                    OpenAiError error = errorMapper.readValue(errorBody, OpenAiError.class);
                    throw new OpenAiHttpException(error, e, e.code());
                } catch (IOException ex) {
                    // couldn't parse OpenAI error
                    throw e;
                }
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
}
