package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.ChatModel;
import com.staringpig.framework.openai.model.OpenAIModel;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChatSession extends BaseSession<ChatModel> implements Session {

    private Long totalToken;
    private final Long tokenThreshold;
    private final List<OnceChat> chats;

    public ChatSession(String user, ChatModel model) {
        super(user, model);
        this.totalToken = 0L;
        this.tokenThreshold = BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(model.getMaxTokens())).longValue();
        this.chats = new ArrayList<>();
    }

    public ChatSession(String user, ChatModel model, Long totalToken, List<OnceChat> chats) {
        super(user, model);
        this.totalToken = totalToken;
        this.tokenThreshold = BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(model.getMaxTokens())).longValue();
        this.chats = chats;
    }

    @Override
    public OpenAIModel.Answer ask(String question) {
        return this.ask(question, Integer.MAX_VALUE);
    }

    @Override
    public OpenAIModel.Answer ask(String question, Integer limitToken) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        for (OnceChat chat : this.chats) {
            chatMessages.add(chat.getUserMessage());
            chatMessages.add(chat.getAssistantMessage());
        }
        OpenAIModel.Answer answer = this.model.ask(user, question, limitToken, chatMessages);
        if (answer.hasModeration()) {
            return answer;
        }
        rebuildSession(question, answer);
        return answer;
    }

    private void rebuildSession(String question, OpenAIModel.Answer answer) {
        OnceChat chat = new OnceChat(answer.getTotalTokens(), new ChatMessage(ChatMessageRole.USER.value(), question),
                new ChatMessage(ChatMessageRole.ASSISTANT.value(), answer.getText()));

        if (this.totalToken >= this.tokenThreshold) {
            this.chats.clear();
            this.totalToken = 0L;
        }

        this.chats.add(chat);
        this.totalToken += answer.getTotalTokens();

        this.model.saveSession(this);
    }

    @Override
    public void ask(String question, Consumer<OpenAIModel.Answer> answerConsumer) {
        ask(question, Integer.MAX_VALUE, answerConsumer);
    }

    @Override
    public void ask(String question, Integer limitToken, Consumer<OpenAIModel.Answer> answerConsumer) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        for (OnceChat chat : this.chats) {
            chatMessages.add(chat.getUserMessage());
            chatMessages.add(chat.getAssistantMessage());
        }
        this.model.ask(user, question, limitToken, chatMessages, answer -> {
            if (!answer.hasModeration()) {
                rebuildSession(question, answer);
            }
            answerConsumer.accept(answer);
        });
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OnceChat {
        private Long tokens;
        private ChatMessage userMessage;
        private ChatMessage assistantMessage;
    }
}
