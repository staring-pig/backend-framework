package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.ministore.order.Order;
import com.staringpig.framework.wechat.ministore.order.OrderDelivery;
import com.staringpig.framework.wechat.ministore.order.OrderPay;
import com.staringpig.framework.wechat.ministore.order.OrderPrice;
import com.staringpig.framework.wechat.ministore.order.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.dreamlu.mica.core.utils.$;
import net.dreamlu.mica.core.utils.DatePattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoQuery {

    @JsonProperty("order_id")
    private Long orderId;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends MiniProgramResult {

        @JsonProperty("order")
        private OrderDTO order;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDTO {
        //订单ID
        @JsonProperty("order_id")
        private Long orderId;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("create_time")
        private String createTime;
        @JsonProperty("update_time")
        private String updateTime;
        @JsonProperty("order_detail")
        private OrderDetailDTO orderDetail;
        @JsonProperty("aftersale_detail")
        private AfterSaleDetailDTO afterSaleDetail;
        @JsonProperty("openid")
        private String openid;
        @JsonProperty("ext_info")
        private ExtInfoDTO extInfo;

        public Order convert() {

            Order.OrderBuilder orderBuilder = Order.builder()
                    .id(this.orderId)
                    .openId(this.openid)
                    .createTime(this.createTime != null ?
                            DatePattern.NORM_DATETIME_FORMAT.parse(this.createTime, LocalDateTime::from)
                            : null)
                    .updateTime(this.updateTime != null ?
                            DatePattern.NORM_DATETIME_FORMAT.parse(this.updateTime, LocalDateTime::from)
                            : null);

            Order.Status status;

            switch (this.status) {
                case 10:
                    status = Order.Status.PAYMENT_PENDING;
                    break;
                case 15:
                    status = Order.Status.PAID_OFF_GROUP_PENDING;
                    break;
                case 16:
                    status = Order.Status.PAID_OFF_WAITING_MERCHANT;
                    break;
                case 17:
                    status = Order.Status.PAID_OFF_WRITE_OFF_PENDING;
                    break;
                case 20:
                    status = Order.Status.TO_BE_DELIVERED;
                    break;
                case 21:
                    status = Order.Status.PARTIAL_DELIVERY;
                    break;
                case 30:
                    status = Order.Status.TO_BE_RECEIVED;
                    break;
                default:
                case 100:
                    status = Order.Status.FINISHED;
                    break;
                case 200:
                    status = Order.Status.AFTER_SALE_CANCEL;
                    break;
                case 250:
                    status = Order.Status.VOLUNTARILY_CANCEL;
                    break;
            }

            orderBuilder.status(status);

            if (this.extInfo != null) {
                orderBuilder
                        .customerNotes(this.extInfo.customerNotes)
                        .merchantNotes(this.extInfo.merchantNotes);
            }

            if (this.orderDetail != null) {
                if ($.isNotEmpty(this.orderDetail.productInfos)) {
                    orderBuilder.orderProducts(this.orderDetail.productInfos.stream()
                            .map(product -> OrderProduct.builder()
                                    .finishAfterSaleSkuCnt(product.finishAftersaleSkuCnt)
                                    .onAfterSaleSkuCnt(product.onAftersaleSkuCnt)
                                    .productId(product.productId)
                                    .salePrice(product.slaePrice)
                                    .skuCnt(product.skuCnt)
                                    .skuId(product.skuId)
                                    .thumbImg(product.thumbImg)
                                    .build())
                            .collect(Collectors.toList()));
                }
                if (this.orderDetail.payInfo != null) {
                    orderBuilder.pay(OrderPay.builder()
                            .payMethod(OrderPay.PayMethod.WECHAT_PAY)
                            .payTime(this.orderDetail.payInfo.payTime != null ?
                                    DatePattern.NORM_DATETIME_FORMAT
                                            .parse(this.orderDetail.payInfo.payTime, LocalDateTime::from)
                                    : null)
                            .prePayId(this.orderDetail.payInfo.prepayId)
                            .prePayTime(this.orderDetail.payInfo.prepayTime != null ?
                                    DatePattern.NORM_DATETIME_FORMAT
                                            .parse(this.orderDetail.payInfo.prepayTime, LocalDateTime::from)
                                    : null)
                            .transactionId(this.orderDetail.payInfo.transactionId)
                            .build());
                }
                if (this.orderDetail.priceInfo != null) {
                    orderBuilder.price(OrderPrice.builder()
                            .discountedPrice(this.orderDetail.priceInfo.discountedPrice)
                            .freight(this.orderDetail.priceInfo.freight)
                            .hasDiscounted(this.orderDetail.priceInfo.isDiscounted)
                            .orderPrice(this.orderDetail.priceInfo.orderPrice)
                            .productPrice(this.orderDetail.priceInfo.productPrice)
                            .build());
                }
                if (this.orderDetail.deliveryInfo != null) {
                    OrderDelivery.OrderDeliveryBuilder deliveryBuilder = OrderDelivery.builder()
                            .deliveryMethod(OrderDelivery.Method.DELIVERY)
                            .deliveryTime(this.orderDetail.deliveryInfo.deliveryTime != null ?
                                    DatePattern.NORM_DATETIME_FORMAT
                                            .parse(this.orderDetail.deliveryInfo.deliveryTime, LocalDateTime::from)
                                    : null)
                            .offlineDeliveryTime(this.orderDetail.deliveryInfo.offlineDeliveryTime)
                            .offlinePickupTime(this.orderDetail.deliveryInfo.offlinePickupTime)
                            .shipDoneTime(this.orderDetail.deliveryInfo.shipDoneTime);

                    if (this.orderDetail.deliveryInfo.addressInfo != null) {
                        deliveryBuilder.address(OrderDelivery.Address.builder()
                                .cityName(this.orderDetail.deliveryInfo.addressInfo.cityName)
                                .countyName(this.orderDetail.deliveryInfo.addressInfo.countyName)
                                .detailInfo(this.orderDetail.deliveryInfo.addressInfo.detailInfo)
                                .telNumber(this.orderDetail.deliveryInfo.addressInfo.telNumber)
                                .nationalCode(this.orderDetail.deliveryInfo.addressInfo.nationalCode)
                                .postalCode(this.orderDetail.deliveryInfo.addressInfo.postalCode)
                                .provinceName(this.orderDetail.deliveryInfo.addressInfo.provinceName)
                                .username(this.orderDetail.deliveryInfo.addressInfo.userName)
                                .houseNumber(this.orderDetail.deliveryInfo.addressInfo.houseNumber)
                                .build());
                    }
                    if (this.orderDetail.deliveryInfo.pickUpAddressInfo != null) {
                        deliveryBuilder.pickupAddress(OrderDelivery.Address.builder()
                                .cityName(this.orderDetail.deliveryInfo.pickUpAddressInfo.cityName)
                                .countyName(this.orderDetail.deliveryInfo.pickUpAddressInfo.countyName)
                                .detailInfo(this.orderDetail.deliveryInfo.pickUpAddressInfo.detailInfo)
                                .telNumber(this.orderDetail.deliveryInfo.pickUpAddressInfo.telNumber)
                                .nationalCode(this.orderDetail.deliveryInfo.pickUpAddressInfo.nationalCode)
                                .postalCode(this.orderDetail.deliveryInfo.pickUpAddressInfo.postalCode)
                                .provinceName(this.orderDetail.deliveryInfo.pickUpAddressInfo.provinceName)
                                .username(this.orderDetail.deliveryInfo.pickUpAddressInfo.userName)
                                .houseNumber(this.orderDetail.deliveryInfo.pickUpAddressInfo.houseNumber)
                                .build());
                    }
                    if ($.isNotEmpty(this.orderDetail.deliveryInfo.deliveryProductInfo)) {
                        List<OrderDelivery.Waybill> waybills = new ArrayList<>();
                        for (DeliveryProductInfoDTO deliveryProductInfoDTO : this.orderDetail.deliveryInfo.deliveryProductInfo) {
                            OrderDelivery.Waybill.WaybillBuilder waybillBuilder = OrderDelivery.Waybill.builder()
                                    .id(deliveryProductInfoDTO.waybillId)
                                    .deliveryId(deliveryProductInfoDTO.deliveryId);

                            if ($.isNotEmpty(deliveryProductInfoDTO.productInfos)) {
                                waybillBuilder.products(deliveryProductInfoDTO.productInfos
                                        .stream()
                                        .map(product -> OrderDelivery.DeliveryProduct.builder()
                                                .productCnt(product.productCnt)
                                                .productId(product.productId)
                                                .skuId(product.skuId)
                                                .build())
                                        .collect(Collectors.toList()));
                            }
                            waybills.add(waybillBuilder.build());
                        }
                        deliveryBuilder.waybills(waybills);
                    }
                    if ($.isNotEmpty(this.orderDetail.deliveryInfo.expressFee)) {
                        List<OrderDelivery.ExpressFee> expressFees = new ArrayList<>();
                        for (ExpressFeeDTO expressFeeDTO : this.orderDetail.deliveryInfo.expressFee) {
                            OrderDelivery.ShippingMethod shippingMethod;
                            switch (expressFeeDTO.shippingMethod) {
                                default:
                                case "ShippingMethod_Express":
                                    shippingMethod = OrderDelivery.ShippingMethod.EXPRESS;
                                    break;
                                case "ShippingMethod_SameCity":
                                    shippingMethod = OrderDelivery.ShippingMethod.SAME_CITY;
                                    break;
                                case "ShippingMethod_Pickup":
                                    shippingMethod = OrderDelivery.ShippingMethod.PICKUP;
                                    break;
                            }
                            expressFees.add(OrderDelivery.ExpressFee.builder()
                                    .distance(expressFeeDTO.distance)
                                    .shippingMethod(shippingMethod)
                                    .build());
                        }
                        deliveryBuilder.expressFees(expressFees);
                    }

                    orderBuilder.delivery(deliveryBuilder.build());
                }
            }

            return orderBuilder.build();
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailDTO {
        @JsonProperty("product_infos")
        private List<ProductInfosDTO> productInfos;
        @JsonProperty("pay_info")
        private PayInfoDTO payInfo;
        @JsonProperty("price_info")
        private PriceInfoDTO priceInfo;
        @JsonProperty("delivery_info")
        private DeliveryInfoDTO deliveryInfo;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PayInfoDTO {
        @JsonProperty("pay_method")
        private String payMethod;
        @JsonProperty("prepay_id")
        private String prepayId;
        @JsonProperty("transaction_id")
        private String transactionId;
        @JsonProperty("prepay_time")
        private String prepayTime;
        @JsonProperty("pay_time")
        private String payTime;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PriceInfoDTO {
        @JsonProperty("product_price")
        private BigDecimal productPrice;
        @JsonProperty("order_price")
        private BigDecimal orderPrice;
        @JsonProperty("freight")
        private BigDecimal freight;
        @JsonProperty("discounted_price")
        private BigDecimal discountedPrice;
        @JsonProperty("is_discounted")
        private Boolean isDiscounted;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryInfoDTO {
        @JsonProperty("delivery_method")
        private String deliveryMethod;
        @JsonProperty("delivery_time")
        private String deliveryTime;
        @JsonProperty("delivery_product_info")
        private List<DeliveryProductInfoDTO> deliveryProductInfo;
        @JsonProperty("address_info")
        private AddressInfoDTO addressInfo;
        @JsonProperty("pickup_address")
        private AddressInfoDTO pickUpAddressInfo;
        @JsonProperty("express_fee")
        private List<ExpressFeeDTO> expressFee;
        @JsonProperty("offline_delivery_time")
        private Long offlineDeliveryTime;
        @JsonProperty("offline_pickup_time")
        private Long offlinePickupTime;
        @JsonProperty("ship_done_time")
        private Long shipDoneTime;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryProductInfoDTO {
        @JsonProperty("waybill_id")
        private String waybillId;
        @JsonProperty("delivery_id")
        private String deliveryId;
        @JsonProperty("product_infos")
        private List<DeliveryProductInfosDTO> productInfos;


    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryProductInfosDTO {
        @JsonProperty("product_id")
        private Long productId;
        @JsonProperty("sku_id")
        private Long skuId;
        @JsonProperty("product_cnt")
        private Long productCnt;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressInfoDTO {
        @JsonProperty("user_name")
        private String userName;
        @JsonProperty("postal_code")
        private String postalCode;
        @JsonProperty("province_name")
        private String provinceName;
        @JsonProperty("city_name")
        private String cityName;
        @JsonProperty("county_name")
        private String countyName;
        @JsonProperty("detail_info")
        private String detailInfo;
        @JsonProperty("national_code")
        private String nationalCode;
        @JsonProperty("tel_number")
        private String telNumber;
        @JsonProperty("house_number")
        private String houseNumber;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExpressFeeDTO {
        @JsonProperty("shipping_method")
        private String shippingMethod;
        @JsonProperty("distance")
        private BigDecimal distance;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductInfosDTO {
        @JsonProperty("product_id")
        private Long productId;
        @JsonProperty("sku_id")
        private Long skuId;
        @JsonProperty("sku_cnt")
        private Long skuCnt;
        @JsonProperty("on_aftersale_sku_cnt")
        private Long onAftersaleSkuCnt;
        @JsonProperty("finish_aftersale_sku_cnt")
        private Long finishAftersaleSkuCnt;
        @JsonProperty("title")
        private String title;
        @JsonProperty("thumb_img")
        private String thumbImg;
        @JsonProperty("sku_attrs")
        private List<SkuAttrsDTO> skuAttrs;
        @JsonProperty("slae_price")
        private BigDecimal slaePrice;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkuAttrsDTO {
        @JsonProperty("attr_key")
        private String attrKey;
        @JsonProperty("attr_value")
        private String attrValue;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AfterSaleDetailDTO {
        @JsonProperty("aftersale_order_list")
        private List<AftersaleOrderListDTO> aftersaleOrderList;
        @JsonProperty("on_aftersale_order_cnt")
        private Integer onAftersaleOrderCnt;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AftersaleOrderListDTO {
        @JsonProperty("aftersale_order_id")
        private Long aftersaleOrderId;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExtInfoDTO {
        @JsonProperty("customer_notes")
        private String customerNotes;
        @JsonProperty("merchant_notes")
        private String merchantNotes;
    }
}
