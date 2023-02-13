package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

/**
 * 文本信息内容
 */
@Getter
public class TextReply extends EventReply {

    /**
     * text 消息
     */
    private final String text;

    public TextReply(String text) {
        this.text = text;
    }
}
