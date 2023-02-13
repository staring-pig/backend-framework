package com.staringpig.framework.wechat.ministore.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单配送
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDelivery {

    /**
     * 快递方式
     */
    private Method deliveryMethod;
    /**
     * 发货时间
     */
    private LocalDateTime deliveryTime;
    /**
     * 快递单
     */
    private List<Waybill> waybills;
    /**
     * 收货地址
     */
    private Address address;
    /**
     * 自提地址
     */
    private Address pickupAddress;
    /**
     * 配送方式
     */
    private List<ExpressFee> expressFees;
    /**
     * 线下配送时间
     */
    private Long offlineDeliveryTime;
    /**
     * 线下自提时间
     */
    private Long offlinePickupTime;
    /**
     * 配送完成时间
     */
    private Long shipDoneTime;


    public enum Method {
        /**
         * 快递
         */
        DELIVERY
    }

    /**
     * 快递单
     */
    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Waybill {
        /**
         * 快递单号
         */
        private String id;
        /**
         * 快递公司编号
         */
        private String deliveryId;
        /**
         * 已发货商品
         */
        private List<DeliveryProduct> products;
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryProduct {

        /**
         * 已发货商品小商店内部商品ID
         */
        private Long productId;
        /**
         * 已发货商品小商店内部skuID
         */
        private Long skuId;
        /**
         * 已发货商品数量
         */
        private Long productCnt;
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        /**
         * 收货人姓名
         */
        private String username;
        /**
         * 邮编
         */
        private String postalCode;
        /**
         * 国标收货地址第一级地址
         */
        private String provinceName;
        /**
         * 国标收货地址第二级地址
         */
        private String cityName;
        /**
         * 国标收货地址第三级地址
         */
        private String countyName;
        /**
         * 详细收货地址信息
         */
        private String detailInfo;
        /**
         * 收货地址国家码
         */
        private String nationalCode;
        /**
         * 收货人手机号码
         */
        private String telNumber;
        /**
         * 自提商家联系号码（自提订单才有该信息）
         */
        private String houseNumber;
    }

    @Setter
    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpressFee {
        /**
         * 线下自提,同城配送距离
         */
        private BigDecimal distance;
        /**
         * 配送方式
         */
        private ShippingMethod shippingMethod;
    }

    public enum ShippingMethod {
        /**
         * 快递
         */
        EXPRESS,
        /**
         * 同城配送
         */
        SAME_CITY,
        /**
         * 自提
         */
        PICKUP,
    }
}
