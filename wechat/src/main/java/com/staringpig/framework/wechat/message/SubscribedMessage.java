package com.staringpig.framework.wechat.message;

/**
 * 订阅消息，继承模板消息，属于模板消息的一种
 *
 * @author niumy
 */
public abstract class SubscribedMessage<T> extends TemplatedMessage<T> {

    protected SubscribedMessage(String templateId, String openId, T data) {
        super(templateId, openId, data);
    }
}
