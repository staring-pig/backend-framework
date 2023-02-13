package com.staringpig.framework.wechat.offiaccount.dispatcher;

import com.staringpig.framework.wechat.offiaccount.OAMessage;
import com.staringpig.framework.wechat.offiaccount.OAMessageHandler;
import net.dreamlu.mica.core.utils.$;

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
        if ($.isEmpty(messageHandlers)) {
            this.messageHandlers = new HashSet<>();
        }
        this.messageHandlers.addAll(oaMessageHandlers);
    }
}
