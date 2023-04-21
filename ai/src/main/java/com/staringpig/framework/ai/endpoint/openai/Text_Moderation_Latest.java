package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.usage.NoneCosting;

public final class Text_Moderation_Latest extends OpenAIModerationModel {
    
    public Text_Moderation_Latest(OpenAIEndpoint endpoint, NoneCosting costing) {
        super("text-moderation-latest", costing, endpoint);
    }
}
