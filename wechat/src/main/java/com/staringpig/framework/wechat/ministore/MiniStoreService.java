package com.staringpig.framework.wechat.ministore;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.ministore.coupon.Coupon;
import com.staringpig.framework.wechat.ministore.coupon.CouponTemplate;
import com.staringpig.framework.wechat.ministore.order.Order;
import com.staringpig.framework.wechat.ministore.order.OrderQuery;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 微信小商城服务
 */
public interface MiniStoreService {

    /**
     * 创建优惠券
     *
     * @param couponTemplate 优惠券模板
     * @return 优惠券
     */
    Coupon create(CouponTemplate couponTemplate);

    /**
     * 发放优惠券
     *
     * @param account 账号
     * @param coupon  优惠券
     */
    void push(OPAppAccount account, Coupon coupon);

    OrderQuery.Result queryOrder(OrderQuery orderQuery);

    /**
     * 查询用户是否已经使用了优惠券
     *
     * @param account 账户
     * @param coupon  优惠券
     * @return 是|否已使用
     */
    boolean couponHasUsed(OPAppAccount account, Coupon coupon);

    /**
     * 查询一段时间的订单总数
     *
     * @param startDateTime 开始时间
     * @param duration      时间间隔
     * @return 订单总数
     */
    Integer countOrdersByCreateTimeDuration(LocalDateTime startDateTime, Duration duration);
//
//    /**
//     * 查询从开始时间 到 期限为止的订单
//     *
//     * @param startDateTime 开始时间
//     * @param duration      时间间隔
//     * @return 订单
//     */
//    Page<Order> queryOrdersByCreateTimeDuration(LocalDateTime startDateTime, Duration duration, Pageable pageable);

    /**
     * 查询一段时间的订单总数
     *
     * @param startDateTime 开始时间
     * @param duration      时间间隔
     * @return 订单总数
     */
    Integer countOrdersByUpdateTimeDuration(LocalDateTime startDateTime, Duration duration);
//
//    /**
//     * 查询从开始时间 到 期限为止的订单
//     *
//     * @param startDateTime 开始时间
//     * @param duration      时间间隔
//     * @return 订单
//     */
//    Page<Order> queryOrdersByUpdateTimeDuration(LocalDateTime startDateTime, Duration duration, Pageable pageable);

    /**
     * 查询订单
     *
     * @param orderId 订单id
     * @return 订单
     */
    Order queryOrder(Long orderId);
}
