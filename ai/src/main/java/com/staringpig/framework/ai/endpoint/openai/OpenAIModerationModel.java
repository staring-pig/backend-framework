package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.endpoint.openai.api.ModerationRequest;
import com.staringpig.framework.ai.model.ModeratingModel;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.NoneUsage;

import java.util.function.Consumer;

public abstract class OpenAIModerationModel extends ModeratingModel<NoneUsage> {

    private final OpenAIEndpoint endpoint;

    public OpenAIModerationModel(String name, Costing<NoneUsage> costing, OpenAIEndpoint endpoint) {
        super(name, costing);
        this.endpoint = endpoint;
    }

    @Override
    public void moderate(String input, Consumer<Moderation> onReply) {
        this.endpoint.moderate(ModerationRequest.builder().model(this.getName()).input(input).build(),
                reply -> onReply.accept(reply.getResults().get(0)));
    }
}
