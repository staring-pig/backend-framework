package com.staringpig.framework.ai.endpoint.openai.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TokenizerAPI {

    @GET("/tokenizer/tokens")
    Call<Long> countTokens(@Query("content") String content);
}
