package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;

/**
 * 回复消息
 */
public abstract class ReplyMessage extends OAMessage {

    protected ReplyMessage(OAUser OAUser, Long createTime, Type type) {
        super(OAUser, createTime, type);
    }
}
