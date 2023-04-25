package com.staringpig.framework.wechat.offiaccount.message.event;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 扫码关注事件
 */
@Getter
public final class ScanSubscribeEvent extends SubscribeEvent {

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    private final String eventKey;
    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    private final String ticket;

    public ScanSubscribeEvent(OAAccount oaAccount, Long createTime, String eventKey, String ticket) {
        super(oaAccount, createTime);
        this.eventKey = eventKey;
        this.ticket = ticket;
    }
}
