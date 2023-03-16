package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.OpenAIModel;

import java.util.function.Consumer;

/**
 * 对话session
 */
public interface Session {

    /**
     * 询问
     *
     * @param question 问题
     * @return 结果
     */
    OpenAIModel.Answer ask(String question);

    /**
     * 询问，返回结果有限制
     *
     * @param question   问题
     * @param limitToken 限制token
     * @return 结果
     */
    OpenAIModel.Answer ask(String question, Integer limitToken);


    /**
     * 询问
     *
     * @param question 问题
     */
    void ask(String question, Consumer<OpenAIModel.Answer> answerConsumer);

    /**
     * 询问，返回结果有限制
     *
     * @param question   问题
     * @param limitToken 限制token
     */
    void ask(String question, Integer limitToken, Consumer<OpenAIModel.Answer> answerConsumer);

    /**
     * session-key
     */
    String key();
}
