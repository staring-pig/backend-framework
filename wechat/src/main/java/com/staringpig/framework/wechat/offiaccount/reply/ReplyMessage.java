package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.OAMessage;

/**
 * 回复消息
 */
public abstract class ReplyMessage extends OAMessage {

    protected ReplyMessage(OPAppAccount opAppAccount, Long createTime, Type type) {
        super(opAppAccount, createTime, type);
    }
}
