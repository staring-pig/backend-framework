package com.staringpig.framework.wechat.message.subscribe;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 消息订阅记录
 * <p>
 * 记录某个账号订阅了某个消息
 *
 * @author niumy
 */
@Getter
public abstract class MessageRegistration {

    /**
     * 订阅消息的账号
     */
    private final Long opAppAccountId;
    /**
     * 订阅的模板id
     */
    private final String messageTemplateId;
    /**
     * 订阅时间
     */
    private final LocalDateTime subscribeDateTime;
    /**
     * 是否取消订阅了
     */
    private Boolean unsubscribe;

    public MessageRegistration(Long opAppAccountId, String messageTemplateId, LocalDateTime subscribeDateTime) {
        this.opAppAccountId = opAppAccountId;
        this.messageTemplateId = messageTemplateId;
        this.subscribeDateTime = subscribeDateTime;
        this.unsubscribe = false;
    }

    public MessageRegistration(Long opAppAccountId, String messageTemplateId, LocalDateTime subscribeDateTime,
                               Boolean unsubscribe) {
        this.opAppAccountId = opAppAccountId;
        this.messageTemplateId = messageTemplateId;
        this.subscribeDateTime = subscribeDateTime;
        this.unsubscribe = unsubscribe;
    }

    /**
     * 取消订阅
     */
    public void unsubscribe() {
        this.unsubscribe = true;
    }
}
