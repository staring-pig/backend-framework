package com.staringpig.framework.openai.model;

import com.staringpig.framework.openai.OpenAI;
import com.staringpig.framework.openai.session.Session;
import com.staringpig.framework.openai.session.SessionManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Open-AI 提供的模型
 */
@AllArgsConstructor
public abstract class OpenAIModel {

    private static final GPT2Tokenizer gpt2Tokenizer = GPT2Tokenizer.fromPretrained("tokenizers/gpt2");
    private static final Pattern SINGLE_CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");
    private static final String MULTI_CHINESE_PATTERN_STRING = "[\\u4e00-\\u9fa5]+";

    /**
     * 模型的ID
     */
    @Getter
    private final String id;
    /**
     * open-ai service
     */
    protected final OpenAI openAI;
    /**
     * open-ai metadata
     */
    protected final OpenAI.Metadata metadata;

    /**
     * 最大tokens
     */
    @Getter
    public final Integer maxTokens;

    /**
     * 每1k tokens 的价格
     */
    @Getter
    protected final BigDecimal pricePerThousandTokens;
    /**
     * session 管理
     */
    private final SessionManager sessionManager;

    /**
     * 计算请求消耗的tokens
     */
    protected static Integer tokens(String content) {
        return chineseTokens(content) +
                gpt2Tokenizer.encode(content.replaceAll(MULTI_CHINESE_PATTERN_STRING, StringUtils.EMPTY)).size();
    }

    private static Integer chineseTokens(String content) {
        int count = 0;
        Matcher matcher = SINGLE_CHINESE_PATTERN.matcher(content);
        while (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                count++;
            }
        }
        return count * 2;
    }

    /**
     * 总tokens消耗的费用
     *
     * @param totalTokens 总tokens
     * @return 消费额度
     */
    protected BigDecimal cost(Long totalTokens) {
        return BigDecimal.valueOf(totalTokens)
                .multiply(this.pricePerThousandTokens)
                .divide(BigDecimal.valueOf(1000), 10, RoundingMode.HALF_DOWN);
    }

    /**
     * 开启一个新的session
     *
     * @param user 用户
     * @return chat-session
     */
    @SuppressWarnings("unchecked")
    public <M extends OpenAIModel> Session<M> openSession(String user) {
        return (Session<M>) sessionManager.openSession(user, this);
    }

    /**
     * 获取当前的session
     *
     * @param user 用户
     * @return 可能为空的 chat-session
     */
    @SuppressWarnings("unchecked")
    public <M extends OpenAIModel> Optional<Session<M>> current(String user) {
        Optional<Session<OpenAIModel>> current = sessionManager.current(user, this);
        return current.map(openAIModelSession -> (Session<M>) openAIModelSession);
    }

    /**
     * 关闭当前的session
     *
     * @param user 用户
     */
    public void closeCurrentSession(String user) {
        sessionManager.closeCurrentSession(user, this);
    }

    /**
     * 保存session
     */
    public <M extends OpenAIModel> void saveSession(Session<M> session) {
        sessionManager.save(session);
    }

    /**
     * 当前session或创建一个新的session
     */
    @SuppressWarnings("unchecked")
    public <M extends OpenAIModel> Session<M> currentOrElseOpenSession(String user) {
        return (Session<M>) current(user).orElse(openSession(user));
    }

    /**
     * 同步问答
     *
     * @param user     用户
     * @param question 问题
     * @return 答案
     */
    public Answer ask(String user, String question) {
        return ask(user, question, Integer.MAX_VALUE);
    }

    /**
     * 同步问答，限制回答字数
     *
     * @param user        用户
     * @param question    问题
     * @param limitTokens 限制字数
     * @return 结果
     */
    public abstract Answer ask(String user, String question, Integer limitTokens);

    /**
     * 异步问答
     *
     * @param user           用户
     * @param question       问题
     * @param answerCallBack 答案call-back
     */
    public void ask(String user, String question, Consumer<Answer> answerCallBack) {
        ask(user, question, Integer.MAX_VALUE, answerCallBack);
    }

    /**
     * 异步问答，限制回答字数
     *
     * @param user           用户
     * @param question       问题
     * @param answerCallBack 答案call-back
     * @param limitTokens    限制大小
     */
    public abstract void ask(String user, String question, Integer limitTokens, Consumer<Answer> answerCallBack);

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Answer {
        /**
         * 总消耗的tokens
         */
        private Long totalTokens;
        /**
         * cost by model and tokens
         */
        private BigDecimal cost;

        /**
         * The generated text. Will include the prompt if CompletionRequest.echo is true
         */
        private String text;
    }
}
