package com.staringpig.framework.wechat.message.template;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.message.OffiAccountTemplatedMessage;
import lombok.Getter;

/**
 * 公众号模板消息的消息模板
 *
 * @param <T> 数据负载类型
 * @author niumy
 */
public abstract class OffiAccountMessageTemplate<T> extends MessageTemplate<T> {

    @Getter
    private String color;

    protected OffiAccountMessageTemplate(String templateId) {
        super(templateId);
    }

    protected OffiAccountMessageTemplate(String templateId, String color) {
        super(templateId);
        this.color = color;
    }

    protected OffiAccountMessageTemplate(String templateId, OPApplication opApplication) {
        super(templateId, opApplication);
    }

    protected OffiAccountMessageTemplate(String templateId, OPApplication opApplication, String color) {
        super(templateId, opApplication);
        this.color = color;
    }

    @Override
    public abstract OffiAccountTemplatedMessage<T> generateMessage(OPAppAccount account, T data);
}
