package com.staringpig.framework.wechat.offiaccount.dispatcher;

import com.staringpig.framework.wechat.offiaccount.OAMessage;
import com.staringpig.framework.wechat.offiaccount.OAMessageHandler;
import net.dreamlu.mica.core.utils.$;

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
        if ($.isEmpty(messageHandlerMap)) {
            this.messageHandlerMap = new ConcurrentHashMap<>();
        }
        for (OAMessageHandler<? extends OAMessage> oaMessageHandler : oaMessageHandlers) {
            this.messageHandlerMap.put(oaMessageHandler.handleMessageType(), oaMessageHandler);
        }
    }
}
