package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.moderation.Moderation;
import net.dreamlu.mica.core.utils.$;
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
        List<Moderation> moderation = super.moderation(question);
        if ($.isNotEmpty(moderation)) {
            return new Answer(moderation);
        }
        return buildAnswer(super.openAI.createChatCompletion(buildRequest(user, question, limitTokens, chatMessages)));
    }

    private Answer buildAnswer(ChatCompletionResult completion) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < super.metadata.getN(); i++) {
            answer.append(StringUtil.trimWhitespace(completion.getChoices().get(i).getMessage().getContent()));
        }
        return new Answer(completion.getUsage().getTotalTokens(),
                super.cost(completion.getUsage().getTotalTokens()), answer.toString());
    }

    private ChatCompletionRequest buildRequest(String user, String question, Integer limitTokens,
                                               List<ChatMessage> chatMessages) {

        List<ChatMessage> newChatMessage = new ArrayList<>(chatMessages);
        ChatMessage currentChatMessage = new ChatMessage(ChatMessageRole.USER.value(), question);
        newChatMessage.add(currentChatMessage);

        int questionTokens = 0;
        for (ChatMessage chatMessage : newChatMessage) {
            questionTokens += 4;
            questionTokens += OpenAIModel.tokens(chatMessage.getRole());
            questionTokens += OpenAIModel.tokens(chatMessage.getContent());
        }

        if (Math.min(this.maxTokens, limitTokens) <= questionTokens) {
            newChatMessage.clear();
            newChatMessage.add(currentChatMessage);
            questionTokens = 4 + OpenAIModel.tokens(currentChatMessage.getRole()) +
                    OpenAIModel.tokens(currentChatMessage.getContent());
        }

        int realTokens = Math.min(this.maxTokens, limitTokens) - questionTokens - 10;

        return ChatCompletionRequest.builder()
                .model(this.getId())
                .messages(newChatMessage)
                .temperature(super.metadata.getTemperature())
                .maxTokens(realTokens <= 0 ? this.maxTokens - questionTokens - 10 : realTokens)
                .user(user)
                .n(super.metadata.getN())
                .stop(this.metadata.getStop())
                .frequencyPenalty(this.metadata.getFrequencyPenalty())
                .logitBias(this.metadata.getLogitBias())
                .presencePenalty(this.metadata.getPresencePenalty())
                .stream(this.metadata.getStream())
                .build();
    }

    public void ask(String user, String question, Integer limitTokens, List<ChatMessage> chatMessages,
                    Consumer<Answer> onAnswer) {
        List<Moderation> moderation = super.moderation(question);
        if ($.isNotEmpty(moderation)) {
            onAnswer.accept(new Answer(moderation));
        }
        super.openAI.createChatCompletion(buildRequest(user, question, limitTokens, chatMessages),
                result -> onAnswer.accept(buildAnswer(result)));
    }

    @Override
    public void ask(String user, String question, Integer limitTokens, Consumer<Answer> onAnswer) {
        ask(user, question, limitTokens, Collections.emptyList(), onAnswer);
    }
}
