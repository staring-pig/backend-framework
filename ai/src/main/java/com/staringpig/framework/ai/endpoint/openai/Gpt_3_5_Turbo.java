package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.openai.utils.UtilsEndpoint;
import com.staringpig.framework.ai.model.ChatContextStore;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.TokensUsage;

import java.math.BigDecimal;
import java.util.UUID;

public class Gpt_3_5_Turbo extends OpenAIChatModel {

    public Gpt_3_5_Turbo(String modelName, Costing<TokensUsage> costing, ChatContextStore chatContextStore,
                         OpenAIEndpoint endpoint, UtilsEndpoint utilsEndpoint, Long maxTokens,
                         BigDecimal pricePerThousandTokens) {
        super(modelName, costing, chatContextStore, endpoint, utilsEndpoint, maxTokens, pricePerThousandTokens);
    }

    @Override
    public String openChat() {
        return UUID.randomUUID().toString();
    }
}
