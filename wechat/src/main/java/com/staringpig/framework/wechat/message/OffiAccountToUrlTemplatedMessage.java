package com.staringpig.framework.wechat.message;

import lombok.Getter;

/**
 * 公众号跳转网页的消息
 *
 * @param <T> 数据类型
 */
public class OffiAccountToUrlTemplatedMessage<T> extends OffiAccountTemplatedMessage<T> {

    /**
     * 跳转的地址
     */
    @Getter
    private String url;

    public OffiAccountToUrlTemplatedMessage(String templateId, String openId, T data) {
        super(templateId, openId, data);
    }

    public OffiAccountToUrlTemplatedMessage(String templateId, String openId, T data, String color, String url) {
        super(templateId, openId, data, color);
        this.url = url;
    }
}
