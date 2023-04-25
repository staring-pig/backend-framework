package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;

/**
 * 来自一个账号的普通消息
 */
public interface OrdinaryMessageHandler<T extends OrdinaryMessage> extends OAMessageHandler<T> {

}
