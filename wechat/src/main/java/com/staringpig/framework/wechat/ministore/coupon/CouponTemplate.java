package com.staringpig.framework.wechat.ministore.coupon;

/**
 * 商品优惠券
 * <p>
 * 属于微信小商城中购买商品所需优惠券
 */
public abstract class CouponTemplate {

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
     * 扩展信息
     */
    private Extend extend;
    /**
     * 推广相关
     */
    private Promote promote;
    /**
     * 领用相关
     */
    private final Receive receive;
    /**
     * 有效期相关
     */
    private final Valid valid;

    public CouponTemplate(String name, ScopeType scopeType, CouponType couponType, Discount discount, Receive receive,
                          Valid valid) {
        this.name = name;
        this.scopeType = scopeType;
        this.couponType = couponType;
        this.discount = discount;
        this.receive = receive;
        this.valid = valid;
    }

    public CouponTemplate(String name, ScopeType scopeType, CouponType couponType, Discount discount, Extend extend,
                          Receive receive, Valid valid) {
        this.name = name;
        this.scopeType = scopeType;
        this.couponType = couponType;
        this.discount = discount;
        this.extend = extend;
        this.receive = receive;
        this.valid = valid;
    }

    public CouponTemplate(String name, ScopeType scopeType, CouponType couponType, Discount discount, Promote promote,
                          Receive receive, Valid valid) {
        this.name = name;
        this.scopeType = scopeType;
        this.couponType = couponType;
        this.discount = discount;
        this.promote = promote;
        this.receive = receive;
        this.valid = valid;
    }

    public CouponTemplate(String name, ScopeType scopeType, CouponType couponType, Discount discount, Extend extend,
                          Promote promote, Receive receive, Valid valid) {
        this.name = name;
        this.scopeType = scopeType;
        this.couponType = couponType;
        this.discount = discount;
        this.extend = extend;
        this.promote = promote;
        this.receive = receive;
        this.valid = valid;
    }

    /**
     * 生成优惠券
     */
    public abstract Coupon generate();
}
