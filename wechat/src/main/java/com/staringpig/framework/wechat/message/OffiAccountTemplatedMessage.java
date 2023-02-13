package com.staringpig.framework.wechat.message;

import lombok.Getter;

/**
 * 公众号模板消息
 *
 * @param <T> 数据类型
 */
public class OffiAccountTemplatedMessage<T> extends TemplatedMessage<T> {

    /**
     * 颜色
     */
    @Getter
    private String color;

    public OffiAccountTemplatedMessage(String templateId, String openId, T data) {
        super(templateId, openId, data);
    }

    public OffiAccountTemplatedMessage(String templateId, String openId, T data, String color) {
        super(templateId, openId, data);
        this.color = color;
    }
}
