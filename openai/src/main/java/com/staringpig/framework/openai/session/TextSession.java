package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.CompletionModel;
import com.staringpig.framework.openai.model.OpenAIModel;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.dreamlu.mica.core.utils.$;
import net.dreamlu.mica.core.utils.StringUtil;

public class TextSession extends BaseSession<CompletionModel> implements Session<CompletionModel> {

    public static final String END_OF_TEXT = "<|endoftext|>";
    public static final String NEW_LINE = "\n";
    public static final String QUESTION = "Q: ";
    public static final String ANSWER = "A: ";

    /**
     * 当前session所产生的completions
     */
    private final OnceCompletion lastCompletion;

    public TextSession(String user, CompletionModel model, OnceCompletion lastCompletion) {
        super(user, model);
        this.lastCompletion = lastCompletion;
    }

    @Override
    public OpenAIModel.Answer ask(String question) {
        return this.model.ask(this.user, buildQuestion(question));
    }

    @Override
    public OpenAIModel.Answer ask(String question, Integer limitToken) {
        return this.model.ask(this.user, buildQuestion(question), limitToken);
    }

    private String buildQuestion(String question) {
        StringBuilder stringBuilder = new StringBuilder();
        if ($.isNotNull(this.lastCompletion)) {
            stringBuilder.append(lastCompletion.getRequest().getPrompt());
            stringBuilder.append(lastCompletion.resultAnswer());
            stringBuilder.append(END_OF_TEXT);
            stringBuilder.append(NEW_LINE);
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
    public static class OnceCompletion {
        /**
         * 请求
         */
        private CompletionRequest request;
        /**
         * 结果
         */
        private CompletionResult result;

        public String resultAnswer() {
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < this.request.getN(); i++) {
                answer.append(StringUtil.trimWhitespace(result.getChoices().get(i).getText()));
            }
            return answer.toString();
        }
    }
}
