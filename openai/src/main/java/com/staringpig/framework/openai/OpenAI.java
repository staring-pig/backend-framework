package com.staringpig.framework.openai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import lombok.Getter;
import okhttp3.Interceptor;

import java.net.Proxy;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public abstract class OpenAI extends OpenAiService {

    private static final ObjectMapper errorMapper = defaultObjectMapper();

    public OpenAI(String token) {
        super(token);
    }

    public OpenAI(String token, Duration timeout) {
        super(token, timeout);
    }

    public OpenAI(OpenAiApi api) {
        super(api);
    }

    public OpenAI(String token, Duration timeout, Proxy proxy) {
        super(defaultRetrofit(defaultClient(token, timeout).newBuilder().proxy(proxy).build(),
                defaultObjectMapper()).create(OpenAiApi.class));
    }

    public OpenAI(String token, Duration timeout, Proxy proxy, Interceptor interceptor) {
        super(defaultRetrofit(defaultClient(token, timeout).newBuilder()
                        .addInterceptor(interceptor)
                        .proxy(proxy)
                        .build(),
                defaultObjectMapper()).create(OpenAiApi.class));
    }

    /**
     * 询问的元数据
     */
    @Getter
    public static class Metadata {
        /**
         * What sampling temperature to use. Higher values means the model will take more risks.
         * Try 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined answer.
         * <p>
         * We generally recommend using this or {@link Metadata#topP} but not both.
         */
        Double temperature;

        /**
         * An alternative to sampling with temperature, called nucleus sampling, where the model considers the results of
         * the tokens with top_p probability mass. So 0.1 means only the tokens comprising the top 10% probability mass are
         * considered.
         * <p>
         * We generally recommend using this or {@link Metadata#temperature} but not both.
         */
        Double topP;

        /**
         * How many completions to generate for each prompt.
         * <p>
         * Because this parameter generates many completions, it can quickly consume your token quota.
         * Use carefully and ensure that you have reasonable settings for {@link com.staringpig.framework.openai.model.OpenAIModel#maxTokens} and {@link Metadata#stop}.
         */
        Integer n;

        /**
         * Whether to stream back partial progress.
         * If set, tokens will be sent as data-only server-sent events as they become available,
         * with the stream terminated by a data: DONE message.
         */
        Boolean stream;

        /**
         * Up to 4 sequences where the API will stop generating further tokens.
         * The returned text will not contain the stop sequence.
         */
        List<String> stop;

        /**
         * Number between 0 and 1 (default 0) that penalizes new tokens based on whether they appear in the text so far.
         * Increases the model's likelihood to talk about new topics.
         */
        Double presencePenalty;

        /**
         * Number between 0 and 1 (default 0) that penalizes new tokens based on their existing frequency in the text so far.
         * Decreases the model's likelihood to repeat the same line verbatim.
         */
        Double frequencyPenalty;

        /**
         * Modify the likelihood of specified tokens appearing in the completion.
         * <p>
         * Maps tokens (specified by their token ID in the GPT tokenizer) to an associated bias value from -100 to 100.
         * <p>
         * https://beta.openai.com/docs/api-reference/completions/create#completions/create-logit_bias
         */
        Map<String, Integer> logitBias;

        private Metadata() {
        }

        private Metadata(Double temperature, Integer n, List<String> stop) {
            this.temperature = temperature;
            this.n = n;
            this.stop = stop;
        }

        private static final Metadata instance = new Metadata(0.7, 1, List.of("/n"));

        public static Metadata defaultInstance() {
            return instance;
        }
    }
}
