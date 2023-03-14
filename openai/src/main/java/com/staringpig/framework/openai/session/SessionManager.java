package com.staringpig.framework.openai.session;

import com.staringpig.framework.openai.model.OpenAIModel;

import java.util.Optional;

/**
 * session management
 */
public interface SessionManager {

    /**
     * 开启一个新的session
     *
     * @param user 用户
     * @return chat-session
     */
    <M extends OpenAIModel> Session<M> openSession(String user, M model);

    /**
     * 获取当前的session
     *
     * @param user 用户
     * @return 可能为空的 chat-session
     */
    <M extends OpenAIModel> Optional<Session<M>> current(String user, M model);

    /**
     * 关闭当前的session
     *
     * @param user 用户
     */
    <M extends OpenAIModel> void closeCurrentSession(String user, M model);

    /**
     * 保存当前session
     */
    <M extends OpenAIModel> void save(Session<M> session);
}
