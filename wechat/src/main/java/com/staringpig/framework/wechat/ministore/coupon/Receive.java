package com.staringpig.framework.wechat.ministore.coupon;

/**
 * 领用相关
 */
public class Receive {
    /**
     * 优惠券领用开始时间戳
     */
    private Long startTime;
    /**
     * 优惠券领用结束时间戳
     */
    private Long endTime;
    /**
     * 优惠券领用总数
     */
    private Long totalNum;
    /**
     * 单人限领张数
     */
    private Long limitNumOnePerson;
}
