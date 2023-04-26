package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 图文消息
 */
@Getter
public final class NewsReplyMessage extends ReplyMessage {

    /**
     * 图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     */
    private final Integer articleCount;
    /**
     * 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
     */
    private final List<Item> articles;

    public NewsReplyMessage(OAUser OAUser, Long createTime, List<Item> articles) {
        super(OAUser, createTime, Type.news);
        this.articles = articles;
        this.articleCount = articles.size();
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public final static class Item {
        /**
         * 图文消息标题
         */
        private final String title;
        /**
         * 图文消息描述
         */
        private final String description;
        /**
         * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
         */
        private final String picUrl;
        /**
         * 点击图文消息跳转链接
         */
        private final String url;
    }
}
