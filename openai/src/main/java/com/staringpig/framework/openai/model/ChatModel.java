package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import net.dreamlu.mica.core.utils.StringUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public abstract class ChatModel extends OpenAIModel {

    protected ChatModel(String id, OpenAI openAI, OpenAI.Metadata metadata, Integer maxTokens,
                        BigDecimal pricePerThousandTokens, SessionManager sessionManager) {
        super(id, openAI, metadata, maxTokens, pricePerThousandTokens, sessionManager);
    }

    @Override
    public Answer ask(String user, String question, Integer limitTokens) {
        return ask(user, question, Integer.MAX_VALUE, Collections.emptyList());
    }

    public Answer ask(String user, String question, List<ChatMessage> chatMessages) {
        return ask(user, question, Integer.MAX_VALUE, chatMessages);
    }

    public Answer ask(String user, String question, Integer limitTokens, List<ChatMessage> chatMessages) {
        List<ChatMessage> newChatMessage = new ArrayList<>(chatMessages);
        newChatMessage.add(new ChatMessage(ChatMessageRole.ASSISTANT.value(), question));
        ChatCompletionResult completion = super.openAI.createChatCompletion(ChatCompletionRequest.builder()
                .model(this.getId())
                .messages(newChatMessage)
                .temperature(super.metadata.getTemperature())
                .maxTokens(Math.min(this.maxTokens, limitTokens) - OpenAIModel.tokens(question))
                .user(user)
                .n(super.metadata.getN())
                .stop(this.metadata.getStop())
                .frequencyPenalty(this.metadata.getFrequencyPenalty())
                .logitBias(this.metadata.getLogitBias())
                .presencePenalty(this.metadata.getPresencePenalty())
                .stream(this.metadata.getStream())
                .build());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < super.metadata.getN(); i++) {
            answer.append(StringUtil.trimWhitespace(completion.getChoices().get(i).getMessage().getContent()));
        }
        return new Answer(super.cost(completion.getUsage().getTotalTokens()), answer.toString());
    }

    @Override
    public void ask(String user, String question, Integer limitTokens, Consumer<Answer> answerCallBack) {

    }
}