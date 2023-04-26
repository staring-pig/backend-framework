package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import lombok.Getter;

/**
 * 文本消息
 */
public final class TextMessage extends OrdinaryMessage {

    @Getter
    private final String content;

    public TextMessage(String id, OAUser OAUser, Long createTime, String content) {
        super(id, OAUser, createTime, Type.text);
        this.content = content;
    }
}
