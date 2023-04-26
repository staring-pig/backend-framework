package com.staringpig.framework.wechat.offiaccount.message;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import lombok.Getter;

/**
 * 公众号消息
 */
@Getter
public abstract class OAMessage {

    /**
     * 接收方微信号
     */
    private final OAUser OAUser;
    /**
     * 消息创建时间
     */
    private final Long createTime;
    /**
     * 消息类型
     */
    private final Type type;

    protected OAMessage(OAUser OAUser, Long createTime, Type type) {
        this.OAUser = OAUser;
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
