package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;

import java.math.BigDecimal;

public class Gpt_3_5_Turbo extends ChatModel {

    private Gpt_3_5_Turbo(OpenAI openAI, SessionManager sessionManager) {
        super("gpt-3.5-turbo", openAI, OpenAI.Metadata.defaultInstance(), 4096, BigDecimal.valueOf(0.002),
                sessionManager);
    }
}
