package com.staringpig.framework.wechat.message.template;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.message.TemplatedMessage;
import lombok.Getter;

/**
 * 消息模板
 *
 * @author niumy
 */
@Getter
public abstract class MessageTemplate<T> {

    /**
     * 模板id
     */
    private final String templateId;
    /**
     * 账号
     */
    private OPApplication opApplication;

    protected void setOpApplication(OPApplication opApplication) {
        this.opApplication = opApplication;
    }

    protected MessageTemplate(String templateId) {
        this.templateId = templateId;
    }

    protected MessageTemplate(String templateId, OPApplication opApplication) {
        this.templateId = templateId;
        this.opApplication = opApplication;
    }

    /**
     * 生成消息
     *
     * @return 具体的消息
     */
    public abstract TemplatedMessage<T> generateMessage(OPAppAccount account, T data);
}
