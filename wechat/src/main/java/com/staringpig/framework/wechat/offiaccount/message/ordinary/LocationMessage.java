package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 地理位置消息
 */
@Getter
public final class LocationMessage extends OrdinaryMessage {

    /**
     * 地理位置纬度
     */
    private final BigDecimal locationX;
    /**
     * 地理位置经度
     */
    private final BigDecimal locationY;
    /**
     * 地图缩放大小
     */
    private final BigDecimal scale;
    /**
     * 地理位置信息
     */
    private final String label;

    public LocationMessage(String id, OAAccount oaAccount, Long createTime, BigDecimal locationX,
                           BigDecimal locationY, BigDecimal scale, String label) {
        super(id, oaAccount, createTime, OAMessage.Type.location);
        this.locationX = locationX;
        this.locationY = locationY;
        this.scale = scale;
        this.label = label;
    }
}
