package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.CompletionModel;
import com.staringpig.framework.openai.model.OpenAIModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.dreamlu.mica.core.utils.$;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TextSession extends BaseSession<CompletionModel> implements Session {

    public static final String END_OF_TEXT = "<|endoftext|>";
    public static final String NEW_LINE = "\n";
    public static final String NEW_THREE_LINE = "\n\n\n";
    public static final String QUESTION = "Q: ";
    public static final String ANSWER = "A: ";

    private final Long tokenThreshold;
    /**
     * 当前session所产生的completions
     */
    private final List<Completion> completions;

    public TextSession(String user, CompletionModel model) {
        super(user, model);
        this.tokenThreshold = BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(model.getMaxTokens())).longValue();
        this.completions = new ArrayList<>();
    }

    public TextSession(String user, CompletionModel model, List<Completion> completions) {
        super(user, model);
        this.tokenThreshold = BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(model.getMaxTokens())).longValue();
        this.completions = completions;
    }

    @Override
    public OpenAIModel.Answer ask(String question) {
        return this.ask(question, Integer.MAX_VALUE);
    }

    @Override
    public OpenAIModel.Answer ask(String question, Integer limitToken) {

        OpenAIModel.Answer answer = this.model.ask(this.user, buildQuestion(question), limitToken);

        if (answer.hasModeration()) {
            return new OpenAIModel.Answer(answer.getModeration());
        }

        rebuildSession(question, answer);

        return answer;
    }

    private void rebuildSession(String question, OpenAIModel.Answer answer) {
        this.completions.add(new Completion(question, answer.getText(), answer.getTotalTokens()));

        if (this.completions.stream().map(Completion::getTotalToken).mapToInt(Long::intValue).sum()
                >= this.tokenThreshold) {
            this.completions.remove(0);
        }

        this.model.saveSession(this);
    }

    @Override
    public void ask(String question, Consumer<OpenAIModel.Answer> answerConsumer) {
        ask(question, Integer.MAX_VALUE, answerConsumer);
    }

    @Override
    public void ask(String question, Integer limitToken, Consumer<OpenAIModel.Answer> answerConsumer) {
        this.model.ask(this.user, buildQuestion(question), limitToken, answer -> {
            if (!answer.hasModeration()) {
                rebuildSession(question, answer);
            }
            answerConsumer.accept(answer);
        });
    }

    private String buildQuestion(String question) {
        StringBuilder stringBuilder = new StringBuilder();
        if ($.isNotEmpty(this.completions)) {
            for (Completion completion : this.completions) {
                stringBuilder.append(QUESTION);
                stringBuilder.append(completion.getQuestion());
                stringBuilder.append(NEW_THREE_LINE);
                stringBuilder.append(ANSWER);
                stringBuilder.append(completion.getAnswer());
                stringBuilder.append(END_OF_TEXT);
                stringBuilder.append(NEW_LINE);
            }
        }
        stringBuilder.append(QUESTION);
        stringBuilder.append(question);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(ANSWER);
        return stringBuilder.toString();
    }


    /**
     * 一次完成
     */
    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Completion {
        /**
         * 请求
         */
        private String question;
        /**
         * 结果
         */
        private String answer;
        /**
         * 总消耗量
         */
        private Long totalToken;
    }
}
