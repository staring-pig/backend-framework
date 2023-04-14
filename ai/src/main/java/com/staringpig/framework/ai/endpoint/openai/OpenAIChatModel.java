package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionResult;
import com.staringpig.framework.ai.endpoint.openai.api.Usage;
import com.staringpig.framework.ai.model.ChatContextStore;
import com.staringpig.framework.ai.model.ChatModel;
import com.staringpig.framework.ai.usage.TokensUsage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.math.BigDecimal;
import java.util.function.Consumer;

public abstract class OpenAIChatModel extends ChatModel<TokensUsage> {

    private final OpenAIEndpoint endpoint;

    public OpenAIChatModel(String modelName, ChatContextStore chatContextStore, OpenAIEndpoint endpoint) {
        super(modelName, chatContextStore);
        this.endpoint = endpoint;
    }

    @Override
    protected void chat(ChatContext context, CompletingPrompt prompt, Consumer<Completion> onReply) {
        ChatCompletionRequest request = ChatCompletionRequest.builder().build();
        this.endpoint.chat(request, new Callback<>() {
            @Override
            public void onResponse(Call<ChatCompletionResult> call, Response<ChatCompletionResult> response) {
                ChatCompletionResult result = response.body();
//                onReply.accept();
                Usage usage = result.getUsage();

            }

            @Override
            public void onFailure(Call<ChatCompletionResult> call, Throwable throwable) {

            }
        });
    }

    @Override
    public BigDecimal costing(TokensUsage usage) {
        return null;
    }
}
