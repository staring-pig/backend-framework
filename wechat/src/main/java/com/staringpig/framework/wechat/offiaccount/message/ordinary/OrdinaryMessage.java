package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import lombok.Getter;

/**
 * 来自一个账号的消息
 */
public abstract class OrdinaryMessage extends OAMessage {

    /**
     * 消息id
     */
    @Getter
    private final String id;

    protected OrdinaryMessage(String id, OAAccount oaAccount, Long createTime, Type type) {
        super(oaAccount, createTime, type);
        this.id = id;
    }
}
