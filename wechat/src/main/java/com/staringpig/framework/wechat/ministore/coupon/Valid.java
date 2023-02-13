package com.staringpig.framework.wechat.ministore.coupon;

/**
 * 有效期相关
 */
public class Valid {
    /**
     * 优惠券有效期类型
     */
    private ValidType validType;
    /**
     * 优惠券有效期天数，和start_time二选一
     */
    private Integer validDayNum;
    /**
     * 优惠券有效期结束时间，若填了start必填
     */
    private Long startTime;
    /**
     * 优惠券有效期开始时间，和valid_day_num二选一
     */
    private Long endTime;
}