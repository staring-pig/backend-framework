package com.staringpig.framework.wechat.offiaccount.message.dispatcher;

import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.OAMessageHandler;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一对一（消息类型->消息处理器）的派发器
 */
public abstract class OneToOneMessageDispatcher extends BaseOAMessageDispatcher implements OAMessageDispatcher {

    /**
     * key转换的处理器
     */
    protected Map<Class<? extends OAMessage>, OAMessageHandler<? extends OAMessage>> messageHandlerMap;

    /**
     * 注册消息处理器
     *
     * @param oaMessageHandlers 公众号消息处理器
     */
    @Override
    public void registerMessageHandler(Collection<OAMessageHandler<? extends OAMessage>> oaMessageHandlers) {
        super.registerMessageHandler(oaMessageHandlers);
        if (AllInOne.isEmpty(messageHandlerMap)) {
            this.messageHandlerMap = new ConcurrentHashMap<>();
        }
        for (OAMessageHandler<? extends OAMessage> oaMessageHandler : oaMessageHandlers) {
            this.messageHandlerMap.put(oaMessageHandler.handleMessageType(), oaMessageHandler);
        }
    }
}
