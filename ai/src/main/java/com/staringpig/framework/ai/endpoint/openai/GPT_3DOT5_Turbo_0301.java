package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.capability.Instruction;
import com.staringpig.framework.ai.capability.Prompt;
import com.staringpig.framework.ai.model.ChatModel;

public class GPT_3DOT5_Turbo_0301 extends ChatModel {

    public GPT_3DOT5_Turbo_0301() {
        super("gpt-3.5-turbo-0301");
    }

    @Override
    public void instructing(Instruction instruction) {

    }

    @Override
    public Completion chat(ChatPrompt prompt) {
        return null;
    }

    @Override
    public Completion complete(Prompt prompt) {
        return null;
    }
}
