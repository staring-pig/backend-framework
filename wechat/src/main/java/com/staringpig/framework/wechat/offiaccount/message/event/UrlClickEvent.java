package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import lombok.Getter;

/**
 * 自定义菜单key为url时
 */
public class UrlClickEvent extends OAEventMessage {

    @Getter
    private final String url;

    public UrlClickEvent(OAUser OAUser, Long createTime, String url) {
        super(OAUser, createTime, Type.VIEW);
        this.url = url;
    }
}
