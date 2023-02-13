package com.staringpig.framework.wechat.message;

import lombok.Getter;

/**
 * 消息
 *
 * @param <T> 消息内容
 * @author niumy
 */
public abstract class Message<T> {

    /**
     * 要发送的用户id
     */
    @Getter
    private final String openId;
    /**
     * 数据
     */
    @Getter
    private final T data;

    protected Message(String openId, T data) {
        this.openId = openId;
        this.data = data;
    }
}
