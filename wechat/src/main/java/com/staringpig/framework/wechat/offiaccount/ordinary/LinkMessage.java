package com.staringpig.framework.wechat.offiaccount.ordinary;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 链接消息
 */
@Getter
public final class LinkMessage extends OrdinaryMessage {

    /**
     * 消息标题
     */
    private final String title;
    /**
     * 消息描述
     */
    private final String description;
    /**
     * 消息链接
     */
    private final String url;

    public LinkMessage(String id, OPAppAccount opAppAccount, Long createTime, String title, String description,
                       String url) {
        super(id, opAppAccount, createTime, Type.link);
        this.title = title;
        this.description = description;
        this.url = url;
    }
}
