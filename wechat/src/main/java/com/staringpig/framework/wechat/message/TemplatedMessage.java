package com.staringpig.framework.wechat.message;

import lombok.Getter;

/**
 * 模板消息
 *
 * @param <T> 消息内容
 */
public abstract class TemplatedMessage<T> extends Message<T> {

    /**
     * 模板id
     */
    @Getter
    private final String templateId;

    protected TemplatedMessage(String templateId, String openId, T data) {
        super(openId, data);
        this.templateId = templateId;
    }
}
