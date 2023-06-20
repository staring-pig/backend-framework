package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;

import java.math.BigDecimal;

public class Gpt_3_5_Turbo_16K extends ChatModel {

    public Gpt_3_5_Turbo_16K(OpenAI openAI, SessionManager sessionManager) {
        super("gpt-3.5-turbo-16k", openAI, OpenAI.Metadata.defaultInstance(), 16384,
                BigDecimal.valueOf(0.003), BigDecimal.valueOf(0.004),
                sessionManager);
    }
}
