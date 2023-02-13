package com.staringpig.framework.wechat.ministore;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.client.MiniProgramClient;
import com.staringpig.framework.wechat.client.WechatAccessTokenClient;
import com.staringpig.framework.wechat.client.api.corp.AccessTokenQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.AccountCouponsQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.CreateCouponCommand;
import com.staringpig.framework.wechat.client.api.miniprogram.OrderInfoQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.OrderListQuery;
import com.staringpig.framework.wechat.client.api.miniprogram.PushCouponCommand;
import com.staringpig.framework.wechat.client.WechatServerException;
import com.staringpig.framework.wechat.ministore.coupon.Coupon;
import com.staringpig.framework.wechat.ministore.coupon.CouponTemplate;
import com.staringpig.framework.wechat.ministore.order.Order;
import com.staringpig.framework.wechat.ministore.order.OrderQuery;
import net.dreamlu.mica.core.utils.$;
import net.dreamlu.mica.core.utils.DatePattern;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 远程小商店服务
 */
public abstract class RemoteMiniStoreService implements MiniStoreService {

    private final OPApplication opApplication;
    private final WechatAccessTokenClient wechatAccessTokenClient;
    private final MiniProgramClient miniProgramClient;

    protected RemoteMiniStoreService(OPApplication opApplication, WechatAccessTokenClient wechatAccessTokenClient,
                                     MiniProgramClient miniProgramClient) {
        this.opApplication = opApplication;
        this.wechatAccessTokenClient = wechatAccessTokenClient;
        this.miniProgramClient = miniProgramClient;
    }

    /**
     * 获取accessToken
     */
    private synchronized String fetchAccessToken() {
        AccessTokenQuery.Result result =
                wechatAccessTokenClient.getAccessToken(opApplication.getAppId(), opApplication.getAppSecret());
        result.isOK();
        return result.getAccessToken();
    }

    /**
     * 创建优惠券
     */
    @Override
    public Coupon create(CouponTemplate couponTemplate) {
        CreateCouponCommand.Result coupon = miniProgramClient.createCoupon(this.fetchAccessToken(),
                CreateCouponCommand.builder().build());
        return null;
    }

    /**
     * 发放优惠券
     *
     * @param account 账号
     * @param coupon  优惠券
     */
    @Override
    public void push(OPAppAccount account, Coupon coupon) {
        miniProgramClient.pushCoupon(this.fetchAccessToken(),
                new PushCouponCommand(account.getOpenId(), coupon.getId())).isOK();
    }

    @Override
    public OrderQuery.Result queryOrder(OrderQuery orderQuery) {
        return miniProgramClient.queryOrder(this.fetchAccessToken(), orderQuery);

    }

    /**
     * 优惠券是否已经使用
     *
     * @param account 账户
     * @param coupon  优惠券
     */
    @Override
    public boolean couponHasUsed(OPAppAccount account, Coupon coupon) {
        AccountCouponsQuery.Result userCoupons = miniProgramClient.queryUserCouponLists(this.fetchAccessToken(),
                new AccountCouponsQuery(account.getOpenId()));

        try {
            userCoupons.isOK();
        } catch (Exception exception) {
            return false;
        }

        if ($.isEmpty(userCoupons.getUserCouponList())) {
            return false;
        }

        for (AccountCouponsQuery.CouponInfo couponInfo : userCoupons.getUserCouponList()) {
            if (Objects.equals(couponInfo.getCouponId(), coupon.getId())
                    && AccountCouponsQuery.Status.COUPON_USER_STATUS_USED.name().equals(couponInfo.getStatus())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Integer countOrdersByCreateTimeDuration(LocalDateTime startDateTime, Duration duration) {

        OrderListQuery.Result result = miniProgramClient.queryOrders(this.fetchAccessToken(),
                OrderListQuery.builder()
                        .createStartTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime))
                        .createEndTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime.plus(duration)))
                        .build());

        result.isOK();

        return result.getTotalNum();
    }

//    @Override
//    public Page<Order> queryOrdersByCreateTimeDuration(LocalDateTime startDateTime, Duration duration,
//                                                       Pageable pageable) {
//
//        OrderListQuery.Result result = miniProgramClient.queryOrders(this.fetchAccessToken(),
//                OrderListQuery.builder()
//                        .createStartTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime))
//                        .createEndTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime.plus(duration)))
//                        .page(pageable.getPageNumber() + 1)
//                        .pageSize(pageable.getPageSize())
//                        .build());
//
//        result.isOK();
//        List<Order> orders = convert(result);
//        return new PageImpl<>(orders, pageable, result.getTotalNum());
//    }

    @Override
    public Integer countOrdersByUpdateTimeDuration(LocalDateTime startDateTime, Duration duration) {
        OrderListQuery.Result result = miniProgramClient.queryOrders(this.fetchAccessToken(),
                OrderListQuery.builder()
                        .updateStartTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime))
                        .updateEndTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime.plus(duration)))
                        .build());

        result.isOK();

        return result.getTotalNum();
    }

//    @Override
//    public Page<Order> queryOrdersByUpdateTimeDuration(LocalDateTime startDateTime, Duration duration,
//                                                       Pageable pageable) {
//        OrderListQuery.Result result = miniProgramClient.queryOrders(this.fetchAccessToken(),
//                OrderListQuery.builder()
//                        .updateStartTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime))
//                        .updateEndTime(DatePattern.NORM_DATETIME_FORMAT.format(startDateTime.plus(duration)))
//                        .page(pageable.getPageNumber() + 1)
//                        .pageSize(pageable.getPageSize())
//                        .build());
//
//        result.isOK();
//        List<Order> orders = convert(result);
//        return new PageImpl<>(orders, pageable, result.getTotalNum());
//    }

    private List<Order> convert(OrderListQuery.Result result) {
        List<Order> orders = new ArrayList<>();
        if ($.isNotEmpty(result.getOrderDTOS())) {
            orders = result.getOrderDTOS().stream().map(OrderListQuery.OrderDTO::convert).collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order queryOrder(Long orderId) {
        OrderInfoQuery.Result result = miniProgramClient.queryOrderInfo(this.fetchAccessToken(),
                new OrderInfoQuery(orderId));

        result.isOK();

        if (result.getOrder() == null) {
            throw new WechatServerException("未找到订单数据");
        }

        return result.getOrder().convert();
    }
}
