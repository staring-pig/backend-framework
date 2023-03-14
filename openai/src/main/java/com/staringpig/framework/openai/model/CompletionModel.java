package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.OpenAI;
import com.staringpig.framework.openai.session.SessionManager;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import net.dreamlu.mica.core.utils.StringUtil;

import java.math.BigDecimal;
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
        CompletionResult completion = super.openAI.createCompletion(CompletionRequest.builder()
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
                .build());
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < super.metadata.getN(); i++) {
            answer.append(StringUtil.trimWhitespace(completion.getChoices().get(i).getText()));
        }
        return new Answer(completion.getUsage().getTotalTokens(),
                super.cost(completion.getUsage().getTotalTokens()), answer.toString());
    }

    @Override
    public void ask(String user, String question, Integer limitTokens, Consumer<Answer> answerCallBack) {

    }
}
