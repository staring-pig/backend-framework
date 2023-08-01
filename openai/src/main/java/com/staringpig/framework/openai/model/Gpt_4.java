package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;

import java.math.BigDecimal;

public class Gpt_4 extends ChatModel {

    public Gpt_4(OpenAI openAI, SessionManager sessionManager) {
        super("gpt-4", openAI, OpenAI.Metadata.defaultInstance(), 8192,
                BigDecimal.valueOf(0.03), BigDecimal.valueOf(0.06),
                sessionManager);
    }
}
