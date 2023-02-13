package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

import java.util.List;

/**
 * 多内容的回复
 */
@Getter
public class MultiContent<T extends EventReply> extends EventReply {

    /**
     * 多内容
     */
    private final List<T> contents;

    public MultiContent(List<T> contents) {
        this.contents = contents;
    }
}
