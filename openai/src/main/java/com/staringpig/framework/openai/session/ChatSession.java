package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.ChatModel;
import com.staringpig.framework.openai.model.OpenAIModel;
import com.theokanning.openai.completion.chat.ChatMessage;

import java.util.List;

public class ChatSession extends BaseSession<ChatModel> implements Session<ChatModel> {

    private final List<ChatMessage> chatMessages;

    public ChatSession(String user, ChatModel model, List<ChatMessage> chatMessages) {
        super(user, model);
        this.chatMessages = chatMessages;
    }

    @Override
    public OpenAIModel.Answer ask(String question) {
        return this.model.ask(user, question, this.chatMessages);
    }

    @Override
    public OpenAIModel.Answer ask(String question, Integer limitToken) {
        return this.model.ask(user, question, limitToken, this.chatMessages);
    }
}
