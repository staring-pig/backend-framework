package com.staringpig.framework.ai.endpoint.openai;

import com.staringpig.framework.ai.capability.Instruction;
import com.staringpig.framework.ai.endpoint.openai.api.ChatCompletionRequest;
import com.staringpig.framework.ai.endpoint.openai.api.ChatMessage;
import com.staringpig.framework.ai.endpoint.openai.api.ChatMessageRole;
import com.staringpig.framework.ai.endpoint.openai.utils.UtilsEndpoint;
import com.staringpig.framework.ai.model.ChatContextStore;
import com.staringpig.framework.ai.model.ChatModel;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.TokensUsage;
import com.staringpig.framework.support.AllInOne;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class OpenAIChatModel extends ChatModel<TokensUsage> {

    private final OpenAIEndpoint endpoint;
    private final UtilsEndpoint utilsEndpoint;
    private final Long maxTokens;
    @Getter
    private final BigDecimal pricePerThousandTokens;

    public OpenAIChatModel(String modelName, Costing<TokensUsage> costing, ChatContextStore chatContextStore,
                           OpenAIEndpoint endpoint, UtilsEndpoint utilsEndpoint, Long maxTokens,
                           BigDecimal pricePerThousandTokens) {
        super(modelName, costing, chatContextStore);
        this.endpoint = endpoint;
        this.utilsEndpoint = utilsEndpoint;
        this.maxTokens = maxTokens;
        this.pricePerThousandTokens = pricePerThousandTokens;
    }

    @Override
    protected void chat(ChatContext context, CompletingPrompt prompt, Consumer<Completion> onReply) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        List<Dialogue> dialogues = context.getDialogues();
        if (AllInOne.isNotEmpty(dialogues)) {
            for (Dialogue dialogue : dialogues) {
                List<Instruction> instructions = dialogue.getInstructions();
                if (AllInOne.isNotEmpty(instructions)) {
                    for (Instruction instruction : instructions) {
                        chatMessages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), instruction.getContent()));
                    }
                }
                chatMessages.add(new ChatMessage(ChatMessageRole.USER.value(), dialogue.getPrompt().getUser(),
                        dialogue.getPrompt().getContent()));
                chatMessages.add(new ChatMessage(ChatMessageRole.ASSISTANT.value(),
                        dialogue.getCompletion().getContent()));
            }
        }
        if (AllInOne.isNotEmpty(context.getNewInstructions())) {
            for (Instruction instruction : context.getNewInstructions()) {
                chatMessages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), instruction.getContent()));
            }
        }
        chatMessages.add(new ChatMessage(ChatMessageRole.USER.value(), prompt.getUser(), prompt.getContent()));
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model(this.getName())
                .messages(chatMessages)
                .n(1)
                .maxTokens(this.maxTokens - utilsEndpoint.countTokens(this.getName(), chatMessages) - 1)
                .build();
        this.endpoint.chat(request, reply -> {
            super.cost(prompt.getUser(), reply.getUsage().convert());
            onReply.accept(new ChatCompletion(context.getChat(),
                    new Completion(reply.getChoices().get(0).getMessage().getContent())));
        });
    }
}
