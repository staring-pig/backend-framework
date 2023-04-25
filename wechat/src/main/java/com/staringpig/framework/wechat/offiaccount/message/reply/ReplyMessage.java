package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;

/**
 * 回复消息
 */
public abstract class ReplyMessage extends OAMessage {

    protected ReplyMessage(OAAccount oaAccount, Long createTime, Type type) {
        super(oaAccount, createTime, type);
    }
}
