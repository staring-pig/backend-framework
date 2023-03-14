package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;

import java.math.BigDecimal;

/**
 * ChatGPT GPT-3 model Davinci text-davinci-003
 */
public class TextDavinci_0_0_3 extends CompletionModel {

    public TextDavinci_0_0_3(OpenAI openAI, SessionManager sessionManager) {
        super("text-davinci-003", openAI, OpenAI.Metadata.defaultInstance(), 4097,
                BigDecimal.valueOf(0.02), "", false, null, null, sessionManager);
    }
}
