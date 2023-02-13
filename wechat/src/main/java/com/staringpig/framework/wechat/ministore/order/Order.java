package com.staringpig.framework.wechat.ministore.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * 订单
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private final static Set<Status> PAID_STATUSES = Set.of(Status.PAID_OFF_GROUP_PENDING,
            Status.PAID_OFF_WAITING_MERCHANT, Status.PAID_OFF_WRITE_OFF_PENDING, Status.TO_BE_DELIVERED,
            Status.PARTIAL_DELIVERY, Status.TO_BE_RECEIVED, Status.FINISHED);

    /**
     * 订单id
     */
    private Long id;
    /**
     * 外部关联订单id
     */
    private String outOrderId;
    /**
     * 客户
     */
    private String openId;
    /**
     * 订单状态
     */
    private Status status;
    /**
     * 订单产品
     */
    private List<OrderProduct> orderProducts;
    /**
     * 订单支付相关
     */
    private OrderPay pay;
    /**
     * 订单价格
     */
    private OrderPrice price;
    /**
     * 订单配送相关
     */
    private OrderDelivery delivery;
    /**
     * 客户备注
     */
    private String customerNotes;
    /**
     * 商家备注
     */
    private String merchantNotes;
    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;
    /**
     * 订单更新时间
     */
    private LocalDateTime updateTime;


    public enum Status {
        /**
         * 待付款
         */
        PAYMENT_PENDING,
        /**
         * 拼团活动 支付成功 等待成团中
         */
        PAID_OFF_GROUP_PENDING,
        /**
         * 支付成功 等待商家接单中 (同城配送, 线下自提)
         */
        PAID_OFF_WAITING_MERCHANT,
        /**
         * 支付成功待核销
         */
        PAID_OFF_WRITE_OFF_PENDING,
        /**
         * 待发货
         */
        TO_BE_DELIVERED,
        /**
         * 部分发货
         */
        PARTIAL_DELIVERY,
        /**
         * 待收货
         */
        TO_BE_RECEIVED,
        /**
         * 完成
         */
        FINISHED,
        /**
         * 全部商品售后之后，订单取消
         */
        AFTER_SALE_CANCEL,
        /**
         * 用户主动取消或待付款超时取消
         */
        VOLUNTARILY_CANCEL,
    }

    /**
     * 是否已支付
     */
    public boolean hasPaid() {
        return PAID_STATUSES.contains(this.status);
    }

    /**
     * 是否已经退货
     */
    public boolean hasReturn() {
        return Status.AFTER_SALE_CANCEL.equals(this.status);
    }
}
