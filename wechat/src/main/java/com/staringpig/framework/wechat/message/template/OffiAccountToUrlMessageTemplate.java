package com.staringpig.framework.wechat.message.template;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.message.OffiAccountToUrlTemplatedMessage;
import lombok.Getter;

/**
 * 公众号跳转小程序模板消息的消息模板
 *
 * @param <T> 数据负载类型
 * @author niumy
 */
public abstract class OffiAccountToUrlMessageTemplate<T> extends OffiAccountMessageTemplate<T> {

    @Getter
    private String url;

    protected OffiAccountToUrlMessageTemplate(String templateId) {
        super(templateId);
    }

    protected OffiAccountToUrlMessageTemplate(String templateId, String color) {
        super(templateId, color);
    }

    protected OffiAccountToUrlMessageTemplate(String templateId, OPApplication opApplication) {
        super(templateId, opApplication);
    }

    protected OffiAccountToUrlMessageTemplate(String templateId, OPApplication opApplication, String url) {
        super(templateId, opApplication);
        this.url = url;
    }

    protected OffiAccountToUrlMessageTemplate(String templateId, String color, OPApplication opApplication) {
        super(templateId, opApplication, color);
    }

    protected OffiAccountToUrlMessageTemplate(String templateId, String color, OPApplication opApplication,
                                              String url) {
        super(templateId, opApplication, color);
        this.url = url;
    }

    @Override
    public abstract OffiAccountToUrlTemplatedMessage<T> generateMessage(OPAppAccount account, T data);
}
