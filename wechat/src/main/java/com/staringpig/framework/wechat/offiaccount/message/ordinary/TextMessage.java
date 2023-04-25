package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 文本消息
 */
public final class TextMessage extends OrdinaryMessage {

    @Getter
    private final String content;

    public TextMessage(String id, OAAccount oaAccount, Long createTime, String content) {
        super(id, oaAccount, createTime, Type.text);
        this.content = content;
    }
}
