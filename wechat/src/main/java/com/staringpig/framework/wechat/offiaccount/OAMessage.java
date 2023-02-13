package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 公众号消息
 */
@Getter
public abstract class OAMessage {

    /**
     * 接收方微信号
     */
    private final OPAppAccount opAppAccount;
    /**
     * 消息创建时间
     */
    private final Long createTime;
    /**
     * 消息类型
     */
    private final Type type;

    protected OAMessage(OPAppAccount opAppAccount, Long createTime, Type type) {
        this.opAppAccount = opAppAccount;
        this.createTime = createTime;
        this.type = type;
    }


    public enum Type {
        /**
         * 文本
         */
        text,
        /**
         * 图片
         */
        image,
        /**
         * 语音
         */
        voice,
        /**
         * 视频
         */
        video,
        /**
         * 小视频
         */
        shortvideo,
        /**
         * 地理位置
         */
        location,
        /**
         * 链接
         */
        link,
        /**
         * 音乐
         */
        music,
        /**
         * 事件消息
         */
        event,
        /**
         * 图文消息
         */
        news,
    }
}
