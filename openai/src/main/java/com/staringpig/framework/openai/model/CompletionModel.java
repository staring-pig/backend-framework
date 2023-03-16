package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.moderation.Moderation;
import net.dreamlu.mica.core.utils.$;
import net.dreamlu.mica.core.utils.StringUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

public abstract class CompletionModel extends OpenAIModel {

    protected final String suffix;

    /**
     * Echo back the prompt in addition to the completion
     */
    protected final Boolean echo;
    /**
     * Generates best_of completions server-side and returns the "best"
     * (the one with the lowest log probability per token).
     * Results cannot be streamed.
     * <p>
     * When used with {@link CompletionRequest#n}, best_of controls the number of candidate completions and n specifies how many to return,
     * best_of must be greater than n.
     */
    protected final Integer bestOf;

    /**
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 10, the API will return a list of the 10 most likely tokens.
     * The API will always return the logprob of the sampled token,
     * so there may be up to logprobs+1 elements in the response.
     */
    protected final Integer logprobs;

    protected CompletionModel(String id, OpenAI openAI, OpenAI.Metadata metadata, Integer maxTokens,
                              BigDecimal pricePerThousandTokens, String suffix, Boolean echo, Integer bestOf,
                              Integer logprobs, SessionManager sessionManager) {
        super(id, openAI, metadata, maxTokens, pricePerThousandTokens, sessionManager);
        this.suffix = suffix;
        this.echo = echo;
        this.bestOf = bestOf;
        this.logprobs = logprobs;
    }

    @Override
    public Answer ask(String user, String question, Integer limitTokens) {
        List<Moderation> moderation = super.moderation(question);
        if ($.isNotEmpty(moderation)) {
            return new Answer(moderation);
        }
        return buildAnswer(super.openAI.createCompletion(buildRequest(user, question, limitTokens)));
    }

    @Override
    public void ask(String user, String question, Integer limitTokens, Consumer<Answer> onAnswer) {
        List<Moderation> moderation = super.moderation(question);
        if ($.isNotEmpty(moderation)) {
            onAnswer.accept(new Answer(moderation));
        }
        super.openAI.createCompletion(buildRequest(user, question, limitTokens),
                completionResult -> onAnswer.accept(buildAnswer(completionResult)));
    }

    private Answer buildAnswer(CompletionResult completion) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < super.metadata.getN(); i++) {
            answer.append(StringUtil.trimWhitespace(completion.getChoices().get(i).getText()));
        }
        return new Answer(completion.getUsage().getTotalTokens(),
                super.cost(completion.getUsage().getTotalTokens()), answer.toString());
    }

    private CompletionRequest buildRequest(String user, String question, Integer limitTokens) {
        return CompletionRequest.builder()
                .model(this.getId())
                .prompt(question)
                .temperature(super.metadata.getTemperature())
                .maxTokens(Math.min(this.maxTokens, limitTokens) - OpenAIModel.tokens(question))
                .user(user)
                .n(super.metadata.getN())
                .stop(this.metadata.getStop())
                .bestOf(this.bestOf)
                .frequencyPenalty(this.metadata.getFrequencyPenalty())
                .echo(this.echo)
                .logitBias(this.metadata.getLogitBias())
                .logprobs(this.logprobs)
                .presencePenalty(this.metadata.getPresencePenalty())
                .stream(this.metadata.getStream())
                .suffix(this.suffix)
                .build();
    }
}
