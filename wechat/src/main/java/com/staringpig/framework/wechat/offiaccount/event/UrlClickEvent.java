package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 自定义菜单key为url时
 */
public class UrlClickEvent extends OAEventMessage {

    @Getter
    private final String url;

    public UrlClickEvent(OPAppAccount opAppAccount, Long createTime, String url) {
        super(opAppAccount, createTime, Type.VIEW);
        this.url = url;
    }
}
