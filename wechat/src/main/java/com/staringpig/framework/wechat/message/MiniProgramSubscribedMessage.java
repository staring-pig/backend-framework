package com.staringpig.framework.wechat.message;

import lombok.Getter;
import net.dreamlu.mica.core.utils.StringPool;

/**
 * 小程序订阅消息
 *
 * @param <T> 消息内容
 */
public final class MiniProgramSubscribedMessage<T> extends SubscribedMessage<T> {

    /**
     * MiniProgramState 跳转小程序类型
     */
    @Getter
    private final MiniProgramState miniProgramState;
    /**
     * 跳转网页时填写
     */
    @Getter
    private final String page;
    /**
     * 进入小程序查看”的语言类型
     */
    @Getter
    private final String lang = "zh_CN";

    public MiniProgramSubscribedMessage(String templateId, String openId, T data) {
        super(templateId, openId, data);
        this.page = StringPool.EMPTY;
        this.miniProgramState = MiniProgramState.formal;
    }

    public MiniProgramSubscribedMessage(String templateId, String openId, T data, String page) {
        super(templateId, openId, data);
        this.page = page;
        this.miniProgramState = MiniProgramState.formal;
    }

    public MiniProgramSubscribedMessage(String templateId, String openId, T data, String page,
                                        MiniProgramState miniProgramState) {
        super(templateId, openId, data);
        this.page = page;
        this.miniProgramState = miniProgramState;
    }

    public enum MiniProgramState {
        /**
         * 开发版
         */
        developer,
        /**
         * 体验版
         */
        trial,
        /**
         * 正式版
         */
        formal,
    }
}
