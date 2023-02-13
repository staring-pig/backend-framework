package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

/**
 * 图文消息
 */
@Getter
public class ArticleReply extends EventReply {

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

    public ArticleReply(String title, String description, String picUrl, String url) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }
}
