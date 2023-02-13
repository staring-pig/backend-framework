package com.staringpig.framework.wechat.ministore.order;

/**
 * @author ldh
 * time 2021-9-1 14:21
 */
public enum OrderStatusEnum {
    //待付款
    STATUS_10(10),
    //拼团活动 支付成功 等待成团中
    STATUS_15(15),
    //支付成功 等待商家接单中 (同城配送, 线下自提)
    STATUS_16(16),
    //支付成功待核销
    STATUS_17(17),
    //待发货
    STATUS_20(20),
    //部分发货
    STATUS_21(21),
    //待收货
    STATUS_30(30),
    //完成
    STATUS_100(100),
    //全部商品售后之后，订单取消
    STATUS_200(200),
    //用户主动取消或待付款超时取消
    STATUS_250(250);
    private Integer status;

    OrderStatusEnum(Integer status) {
        this.status = status;
    }
}
