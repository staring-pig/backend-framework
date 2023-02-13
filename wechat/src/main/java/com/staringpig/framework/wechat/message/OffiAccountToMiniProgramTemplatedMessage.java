package com.staringpig.framework.wechat.message;

import com.staringpig.framework.wechat.OPApplication;
import lombok.Getter;

/**
 * 公众号跳转小程序的模板消息
 *
 * @param <T> 数据类型
 */
public class OffiAccountToMiniProgramTemplatedMessage<T> extends OffiAccountTemplatedMessage<T> {

    /**
     * 要跳转的小程序
     */
    @Getter
    private final OPApplication miniProgram;
    /**
     * 小程序对应的页面
     */
    @Getter
    private String pagePath;
    /**
     * 小程序跳转默认带路径
     */
    @Getter
    private final String url = "http://mp.weixin.qq.com";

    public OffiAccountToMiniProgramTemplatedMessage(String templateId, String openId, T data,
                                                    OPApplication miniProgram) {
        super(templateId, openId, data);
        this.miniProgram = miniProgram;
    }

    public OffiAccountToMiniProgramTemplatedMessage(String templateId, String openId, T data, String color,
                                                    OPApplication miniProgram) {
        super(templateId, openId, data, color);
        this.miniProgram = miniProgram;
    }

    public OffiAccountToMiniProgramTemplatedMessage(String templateId, String openId, T data, String color,
                                                    OPApplication miniProgram, String pagePath) {
        super(templateId, openId, data, color);
        this.miniProgram = miniProgram;
        this.pagePath = pagePath;
    }
}
