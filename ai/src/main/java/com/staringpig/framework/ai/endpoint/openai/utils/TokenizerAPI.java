package com.staringpig.framework.ai.endpoint.openai.utils;

import com.staringpig.framework.ai.endpoint.openai.api.CountTokensCommand;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenizerAPI {

    @POST("/tokenizer/tokens")
    Call<Long> countTokens(@Body CountTokensCommand command);
}
