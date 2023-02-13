package com.staringpig.framework.wechat.message.template;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.message.OffiAccountToMiniProgramTemplatedMessage;
import lombok.Getter;

/**
 * 公众号跳转小程序模板消息的消息模板
 *
 * @param <T> 数据负载类型
 * @author niumy
 */
public abstract class OffiAccountToMiniProgramMessageTemplate<T> extends OffiAccountMessageTemplate<T> {

    @Getter
    private final OPApplication miniProgram;
    @Getter
    private String pagePath;

    protected OffiAccountToMiniProgramMessageTemplate(String templateId, OPApplication miniProgram) {
        super(templateId);
        this.miniProgram = miniProgram;
    }

    protected OffiAccountToMiniProgramMessageTemplate(String templateId, String color, OPApplication miniProgram) {
        super(templateId, color);
        this.miniProgram = miniProgram;
    }

    protected OffiAccountToMiniProgramMessageTemplate(String templateId, OPApplication opApplication,
                                                      OPApplication miniProgram) {
        super(templateId, opApplication);
        this.miniProgram = miniProgram;
    }

    protected OffiAccountToMiniProgramMessageTemplate(String templateId, OPApplication opApplication,
                                                      OPApplication miniProgram, String pagePath) {
        super(templateId, opApplication);
        this.miniProgram = miniProgram;
        this.pagePath = pagePath;
    }

    protected OffiAccountToMiniProgramMessageTemplate(String templateId, OPApplication opApplication, String color,
                                                      OPApplication miniProgram) {
        super(templateId, opApplication, color);
        this.miniProgram = miniProgram;
    }

    protected OffiAccountToMiniProgramMessageTemplate(String templateId, OPApplication opApplication, String color,
                                                      OPApplication miniProgram, String pagePath) {
        super(templateId, opApplication, color);
        this.miniProgram = miniProgram;
        this.pagePath = pagePath;
    }

    @Override
    public abstract OffiAccountToMiniProgramTemplatedMessage<T> generateMessage(OPAppAccount account, T data);
}
