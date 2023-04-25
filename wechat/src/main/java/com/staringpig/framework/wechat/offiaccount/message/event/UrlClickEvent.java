package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 自定义菜单key为url时
 */
public class UrlClickEvent extends OAEventMessage {

    @Getter
    private final String url;

    public UrlClickEvent(OAAccount oaAccount, Long createTime, String url) {
        super(oaAccount, createTime, Type.VIEW);
        this.url = url;
    }
}
