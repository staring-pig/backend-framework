package com.staringpig.framework.wechat.offiaccount.message.dispatcher;

import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;

import java.util.Collection;
import java.util.HashSet;

/**
 * 基础的消息派发机制
 */
public abstract class BaseOAMessageDispatcher implements OAMessageDispatcher {

    /**
     * 持有消息处理器
     */
    protected Collection<OAMessageHandler<? extends OAMessage>> messageHandlers;

    /**
     * 注册消息处理器
     *
     * @param oaMessageHandlers 公众号消息处理器
     */
    @Override
    public void registerMessageHandler(Collection<OAMessageHandler<? extends OAMessage>> oaMessageHandlers) {
        if (AllInOne.isEmpty(messageHandlers)) {
            this.messageHandlers = new HashSet<>();
        }
        this.messageHandlers.addAll(oaMessageHandlers);
    }
}