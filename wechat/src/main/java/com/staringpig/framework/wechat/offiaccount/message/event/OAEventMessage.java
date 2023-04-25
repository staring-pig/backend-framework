package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import lombok.Getter;

/**
 * 公众号事件
 */
public abstract class OAEventMessage extends OAMessage {

    /**
     * 事件类型
     */
    @Getter
    private final Type event;

    protected OAEventMessage(OAAccount oaAccount, Long createTime, Type event) {
        super(oaAccount, createTime, OAMessage.Type.event);
        this.event = event;
    }

    /**
     * 事件类型
     */
    public enum Type {
        /**
         * 订阅
         */
        subscribe,
        /**
         * 取消订阅
         */
        unsubscribe,
        /**
         * 上报地理位置事件
         */
        LOCATION,
        /**
         * 自定义菜单事件
         */
        CLICK,
        /**
         * 已关注的人扫码进来
         */
        SCAN,
        /**
         * 点击菜单跳转链接时的事件推送
         */
        VIEW,
        /**
         * 浏览关联的小程序
         */
        view_miniprogram,
    }
}
