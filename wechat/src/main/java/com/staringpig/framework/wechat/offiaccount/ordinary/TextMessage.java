package com.staringpig.framework.wechat.offiaccount.ordinary;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 文本消息
 */
public final class TextMessage extends OrdinaryMessage {

    @Getter
    private final String content;

    public TextMessage(String id, OPAppAccount opAppAccount, Long createTime, String content) {
        super(id, opAppAccount, createTime, Type.text);
        this.content = content;
    }
}
