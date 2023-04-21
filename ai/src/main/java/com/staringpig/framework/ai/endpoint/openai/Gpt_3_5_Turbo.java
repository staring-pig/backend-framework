package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.openai.utils.UtilsEndpoint;
import com.staringpig.framework.ai.model.ChatContextStore;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.TokensUsage;

import java.math.BigDecimal;

public final class Gpt_3_5_Turbo extends OpenAIChatModel {

    public Gpt_3_5_Turbo(Costing<TokensUsage> costing, ChatContextStore chatContextStore, OpenAIEndpoint endpoint,
                         UtilsEndpoint utilsEndpoint) {
        super("gpt-3.5-turbo", costing, chatContextStore, endpoint, utilsEndpoint, 4096L,
                BigDecimal.valueOf(0.002));
    }

}
