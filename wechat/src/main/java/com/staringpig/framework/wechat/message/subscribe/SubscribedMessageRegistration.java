package com.staringpig.framework.wechat.message.subscribe;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * "订阅消息（SubscribedMessage）"的消息票根
 * <p>
 * 订阅消息有个特性：只能发送一次，所以，当发送一次消息之后，不能继续发送，所以每次发送的时候需要取消订阅
 *
 * @author niumy
 */
@Getter
public abstract class SubscribedMessageRegistration extends MessageRegistration {

    /**
     * 是否已经发送过了
     */
    private Boolean hasSent;

    public SubscribedMessageRegistration(Long opAppAccountId, String messageTemplateId,
                                         LocalDateTime subscribeDateTime) {
        super(opAppAccountId, messageTemplateId, subscribeDateTime);
        this.hasSent = false;
    }

    public SubscribedMessageRegistration(Long opAppAccountId, String messageTemplateId,
                                         LocalDateTime subscribeDateTime, Boolean unsubscribe) {
        super(opAppAccountId, messageTemplateId, subscribeDateTime, unsubscribe);
        this.hasSent = false;
    }

    public SubscribedMessageRegistration(Long opAppAccountId, String messageTemplateId,
                                         LocalDateTime subscribeDateTime, Boolean unsubscribe, Boolean hasSent) {
        super(opAppAccountId, messageTemplateId, subscribeDateTime, unsubscribe);
        this.hasSent = hasSent;
    }

    /**
     * 记录已经发送了
     */
    public void recordSent() {
        this.hasSent = true;
    }
}
