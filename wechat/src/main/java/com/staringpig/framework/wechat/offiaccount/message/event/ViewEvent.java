package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
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

    public ViewEvent(OAUser OAUser, Long createTime, String url) {
        super(OAUser, createTime, Type.VIEW);
        this.url = url;
    }
}
