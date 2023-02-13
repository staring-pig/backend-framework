package com.staringpig.framework.wechat.connect.event.reply;

import java.util.List;

/**
 * 新闻回复
 */
public final class NewsReply extends MultiContent<ArticleReply> {

    public NewsReply(List<ArticleReply> contents) {
        super(contents);
    }
}
