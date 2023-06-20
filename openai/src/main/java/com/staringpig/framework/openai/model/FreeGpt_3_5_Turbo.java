package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;

import java.math.BigDecimal;

public class FreeGpt_3_5_Turbo extends ChatModel {

    public FreeGpt_3_5_Turbo(OpenAI openAI, SessionManager sessionManager) {
        super("gpt-3.5-turbo", openAI, OpenAI.Metadata.defaultInstance(), 4096, BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.05), sessionManager);
    }
}
