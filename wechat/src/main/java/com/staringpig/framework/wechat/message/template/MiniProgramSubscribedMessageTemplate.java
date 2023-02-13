package com.staringpig.framework.wechat.message.template;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.message.MiniProgramSubscribedMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 小程序订阅消息模板
 *
 * @param <T> 数据负载类型
 * @author niumy
 */
public abstract class MiniProgramSubscribedMessageTemplate<T> extends MessageTemplate<T> {

    /**
     * 跳转路径
     */
    @Getter
    private String page;
    /**
     * 跳转的小程序版本
     */
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private MiniProgramSubscribedMessage.MiniProgramState miniProgramState;

    protected MiniProgramSubscribedMessageTemplate(String templateId) {
        super(templateId);
    }

    protected MiniProgramSubscribedMessageTemplate(String templateId, String page) {
        super(templateId);
        this.page = page;
    }

    protected MiniProgramSubscribedMessageTemplate(String templateId, OPApplication opApplication) {
        super(templateId, opApplication);
    }

    protected MiniProgramSubscribedMessageTemplate(String templateId, OPApplication opApplication, String page) {
        super(templateId, opApplication);
        this.page = page;
    }

    @Override
    public abstract MiniProgramSubscribedMessage<T> generateMessage(OPAppAccount account, T data);
}
