package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.ChatModel;
import com.staringpig.framework.openai.model.OpenAIModel;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChatSession extends BaseSession<ChatModel> implements Session<ChatModel> {

    private Long totalToken;
    private final Long tokenThreshold;
    private final List<ChatMessage> chatMessages;

    public ChatSession(String user, ChatModel model) {
        super(user, model);
        this.totalToken = 0L;
        this.tokenThreshold = BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(model.getMaxTokens())).longValue();
        this.chatMessages = new ArrayList<>();
    }

    public ChatSession(String user, ChatModel model, Long totalToken, List<ChatMessage> chatMessages) {
        super(user, model);
        this.totalToken = totalToken;
        this.tokenThreshold = BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(model.getMaxTokens())).longValue();
        this.chatMessages = chatMessages;
    }

    @Override
    public OpenAIModel.Answer ask(String question) {
        return this.ask(question, Integer.MAX_VALUE);
    }

    @Override
    public OpenAIModel.Answer ask(String question, Integer limitToken) {
        OpenAIModel.Answer answer = this.model.ask(user, question, limitToken, this.chatMessages);

        if (answer.hasModeration()) {
            return answer;
        }

        this.chatMessages.add(new ChatMessage(ChatMessageRole.USER.value(), question));
        this.chatMessages.add(new ChatMessage(ChatMessageRole.ASSISTANT.value(), answer.getText()));
        this.totalToken += answer.getTotalTokens();

        // 清理一下
        if (this.totalToken >= this.tokenThreshold) {
            this.chatMessages.remove(0);
        }

        this.model.saveSession(this);
        return answer;
    }
}
