package com.staringpig.framework.openai;

import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;

public class OpenAI extends OpenAiService {

    public OpenAI(String token) {
        super(token);
    }

    public OpenAI(String token, Duration timeout) {
        super(token, timeout);
    }

    public OpenAI(OpenAiApi api) {
        super(api);
    }
}
