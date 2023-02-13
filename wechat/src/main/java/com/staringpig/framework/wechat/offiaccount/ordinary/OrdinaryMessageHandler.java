package com.staringpig.framework.wechat.offiaccount.ordinary;

import com.staringpig.framework.wechat.offiaccount.OAMessageHandler;

/**
 * 来自一个账号的普通消息
 */
public interface OrdinaryMessageHandler<T extends OrdinaryMessage> extends OAMessageHandler<T> {

}
