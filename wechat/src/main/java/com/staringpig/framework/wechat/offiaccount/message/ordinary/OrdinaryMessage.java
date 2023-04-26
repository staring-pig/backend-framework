package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
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

    protected OrdinaryMessage(String id, OAUser OAUser, Long createTime, Type type) {
        super(OAUser, createTime, type);
        this.id = id;
    }
}
