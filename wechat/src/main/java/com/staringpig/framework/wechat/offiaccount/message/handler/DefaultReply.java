package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.TextReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;

import java.time.Instant;

/**
 * 默认欢迎页面配置
 */
public class DefaultReply {

    private final String content;
    private final String welcomeContent;

    public DefaultReply(String content, String welcomeContent) {
        this.content = content;
        this.welcomeContent = welcomeContent;
    }

    public ReplyMessage generate(OAUser user) {
        return new TextReplyMessage(user, Instant.now().getEpochSecond(), content);
    }

    public ReplyMessage generateWelcome(OAUser user) {
        return new TextReplyMessage(user, Instant.now().getEpochSecond(), welcomeContent);
    }
}
