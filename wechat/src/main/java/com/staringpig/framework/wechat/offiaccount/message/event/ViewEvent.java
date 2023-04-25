package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 扫码关注事件
 */
@Getter
public final class ViewEvent extends OAEventMessage {

    /**
     * 事件KEY值，设置的跳转URL
     */
    private final String url;

    public ViewEvent(OAAccount oaAccount, Long createTime, String url) {
        super(oaAccount, createTime, Type.VIEW);
        this.url = url;
    }
}
