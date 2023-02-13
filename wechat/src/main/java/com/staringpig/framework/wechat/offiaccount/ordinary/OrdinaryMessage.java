package com.staringpig.framework.wechat.offiaccount.ordinary;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.OAMessage;
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

    protected OrdinaryMessage(String id, OPAppAccount opAppAccount, Long createTime, Type type) {
        super(opAppAccount, createTime, type);
        this.id = id;
    }
}
