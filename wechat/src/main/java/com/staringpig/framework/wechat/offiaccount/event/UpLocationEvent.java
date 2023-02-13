package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * 上报地理位置事件
 */
@Getter
public final class UpLocationEvent extends OAEventMessage {

    /**
     * 地理位置纬度
     */
    private final BigDecimal latitude;
    /**
     * 地理位置经度
     */
    private final BigDecimal longitude;
    /**
     * 地理位置精度
     */
    private final BigDecimal precision;

    public UpLocationEvent(OPAppAccount opAppAccount, Long createTime, BigDecimal latitude, BigDecimal longitude,
                           BigDecimal precision) {
        super(opAppAccount, createTime, Type.LOCATION);
        this.latitude = latitude;
        this.longitude = longitude;
        this.precision = precision;
    }
}
