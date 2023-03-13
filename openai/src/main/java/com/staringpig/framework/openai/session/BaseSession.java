package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.OpenAIModel;

public abstract class BaseSession<E extends OpenAIModel> implements Session<E> {

    /**
     * 用户
     */
    protected String user;
    /**
     * 使用的模型
     */
    protected E model;

    protected BaseSession(String user, E model) {
        this.user = user;
        this.model = model;
    }
}
