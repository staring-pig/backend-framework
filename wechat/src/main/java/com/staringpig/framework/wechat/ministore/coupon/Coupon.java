package com.staringpig.framework.wechat.ministore.coupon;

import lombok.Getter;

/**
 * 优惠券本券
 */
@Getter
public class Coupon {

    /**
     * 优惠券id
     */
    private final Long id;
    /**
     * 优惠券名称
     */
    private final String name;
    /**
     * 影响范围类型
     */
    private final ScopeType scopeType;
    /**
     * 优惠券类型
     */
    private final CouponType couponType;
    /**
     * 折扣信息相关
     */
    private final Discount discount;
    /**
     * 领用相关
     */
    private final Receive receive;
    /**
     * 有效期相关
     */
    private final Valid valid;

    /**
     * 扩展信息
     */
    private Extend extend;
    /**
     * 推广相关
     */
    private Promote promote;
    /**
     * 库存相关信息
     */
    private CouponStock stock;

    public Coupon(Long id, String name, ScopeType scopeType, CouponType couponType, Discount discount, Receive receive,
                  Valid valid, Extend extend, Promote promote, CouponStock stock) {
        this.id = id;
        this.name = name;
        this.scopeType = scopeType;
        this.couponType = couponType;
        this.discount = discount;
        this.receive = receive;
        this.valid = valid;
        this.extend = extend;
        this.promote = promote;
        this.stock = stock;
    }
}
