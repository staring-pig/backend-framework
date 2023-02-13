package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.account.OPAppAccount;
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

    public ScanSubscribeEvent(OPAppAccount opAppAccount, Long createTime, String eventKey, String ticket) {
        super(opAppAccount, createTime);
        this.eventKey = eventKey;
        this.ticket = ticket;
    }

    public ScanSubscribeEvent(String openId, Long createTime, String eventKey, String ticket) {
        super(openId, createTime);
        this.eventKey = eventKey;
        this.ticket = ticket;
    }
}
