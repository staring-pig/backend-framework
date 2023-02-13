package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 文本消息
 */
@Getter
public final class TextReplyMessage extends ReplyMessage {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private final String content;

    public TextReplyMessage(OPAppAccount opAppAccount, Long createTime, String content) {
        super(opAppAccount, createTime, Type.text);
        this.content = content;
    }
}
