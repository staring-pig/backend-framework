package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.OpenAIModel;

/**
 * 对话session
 */
public interface Session<M extends OpenAIModel> {

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
}
