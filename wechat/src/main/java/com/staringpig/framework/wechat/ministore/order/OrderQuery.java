package com.staringpig.framework.wechat.ministore.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dreamlu.mica.core.utils.DatePattern;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ldh
 * time 2021-9-1 14:31
 */
@NoArgsConstructor
@Data
public class OrderQuery {
    /**
     * 订单创建时间的搜索开始时间
     */
    @JsonProperty("start_create_time")

    private String startCreateTime = DatePattern.NORM_DATETIME_FORMAT.format(LocalDateTime.now().minusDays(10));
    /**
     * 订单创建时间的搜索结束时间
     */
    @JsonProperty("end_create_time")
    private String endCreateTime = DatePattern.NORM_DATETIME_FORMAT.format(LocalDateTime.now());
    /**
     * 订单状态
     */
    @JsonProperty("status")
    private Integer status;
    /**
     * 第几页（最小填1）
     */
    @JsonProperty("page")
    private Integer page = 1;
    /**
     * 每页数量(不超过10,000)
     */
    @JsonProperty("page_size")
    private Integer pageSize = 9999;
    /**
     * 1:小商店,2:CPS带货
     */
    @JsonProperty("source")
    private Integer source = 1;
    @Builder
    public OrderQuery(String startCreateTime, String endCreateTime, Integer status, Integer page, Integer pageSize, Integer source) {
        this.startCreateTime = startCreateTime;
        this.endCreateTime = endCreateTime;
        this.status = status;
        this.page = page;
        this.pageSize = pageSize;
        this.source = source;
    }

    /**
     * 具体参数说明见：
     * https://developers.weixin.qq.com/miniprogram/dev/framework/ministore/minishopopencomponent/API/order/get_order_list.html#%E5%9B%9E%E5%8C%85%E5%8F%82%E6%95%B0%E8%AF%B4%E6%98%8E
     */
    @NoArgsConstructor
    @Data
    public static class Result {

        @JsonProperty("errcode")
        private Integer errcode = 0;
        @JsonProperty("errmsg")
        private String errmsg = "ok";
        @JsonProperty("orders")
        private List<OrdersDTO> orders;
        @JsonProperty("total_num")
        private Integer totalNum;

        public Boolean isSuccess() {
            return errcode == 0 && "ok".equals(errmsg);
        }

        @NoArgsConstructor
        @Data
        public static class OrdersDTO {
            //订单ID
            @JsonProperty("order_id")
            private Integer orderId;
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

            @NoArgsConstructor
            @Data
            public static class OrderDetailDTO {
                @JsonProperty("product_infos")
                private List<ProductInfosDTO> productInfos;
                @JsonProperty("pay_info")
                private PayInfoDTO payInfo;
                @JsonProperty("price_info")
                private PriceInfoDTO priceInfo;
                @JsonProperty("delivery_info")
                private DeliveryInfoDTO deliveryInfo;

                @NoArgsConstructor
                @Data
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

                @NoArgsConstructor
                @Data
                public static class PriceInfoDTO {
                    @JsonProperty("product_price")
                    private Integer productPrice;
                    @JsonProperty("order_price")
                    private Integer orderPrice;
                    @JsonProperty("freight")
                    private Integer freight;
                    @JsonProperty("discounted_price")
                    private Integer discountedPrice;
                    @JsonProperty("is_discounted")
                    private Boolean isDiscounted;
                }

                @NoArgsConstructor
                @Data
                public static class DeliveryInfoDTO {
                    @JsonProperty("delivery_method")
                    private String deliveryMethod;
                    @JsonProperty("delivery_time")
                    private String deliveryTime;
                    @JsonProperty("delivery_product_info")
                    private DeliveryProductInfoDTO deliveryProductInfo;
                    @JsonProperty("address_info")
                    private AddressInfoDTO addressInfo;
                    @JsonProperty("express_fee")
                    private List<ExpressFeeDTO> expressFee;
                    @JsonProperty("offline_delivery_time")
                    private Integer offlineDeliveryTime;
                    @JsonProperty("offline_pickup_time")
                    private Integer offlinePickupTime;
                    @JsonProperty("ship_done_time")
                    private String shipDoneTime;

                    @NoArgsConstructor
                    @Data
                    public static class DeliveryProductInfoDTO {
                        @JsonProperty("waybill_id")
                        private String waybillId;
                        @JsonProperty("delivery_id")
                        private String deliveryId;
                        @JsonProperty("product_infos")
                        private List<ProductInfosDTO> productInfos;

                        @NoArgsConstructor
                        @Data
                        public static class ProductInfosDTO {
                            @JsonProperty("product_id")
                            private Integer productId;
                            @JsonProperty("sku_id")
                            private Integer skuId;
                            @JsonProperty("product_cnt")
                            private Integer productCnt;
                        }
                    }

                    @NoArgsConstructor
                    @Data
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
                    }

                    @NoArgsConstructor
                    @Data
                    public static class ExpressFeeDTO {
                        @JsonProperty("shipping_method")
                        private String shippingMethod;
                        @JsonProperty("distance")
                        private Integer distance;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class ProductInfosDTO {
                    @JsonProperty("product_id")
                    private Integer productId;
                    @JsonProperty("sku_id")
                    private Integer skuId;
                    @JsonProperty("sku_cnt")
                    private Integer skuCnt;
                    @JsonProperty("on_aftersale_sku_cnt")
                    private Integer onAftersaleSkuCnt;
                    @JsonProperty("finish_aftersale_sku_cnt")
                    private Integer finishAftersaleSkuCnt;
                    @JsonProperty("title")
                    private String title;
                    @JsonProperty("thumb_img")
                    private String thumbImg;
                    @JsonProperty("sku_attrs")
                    private List<SkuAttrsDTO> skuAttrs;
                    @JsonProperty("slae_price")
                    private Integer slaePrice;

                    @NoArgsConstructor
                    @Data
                    public static class SkuAttrsDTO {
                        @JsonProperty("attr_key")
                        private String attrKey;
                        @JsonProperty("attr_value")
                        private String attrValue;
                    }
                }
            }

            @NoArgsConstructor
            @Data
            public static class AfterSaleDetailDTO {
                @JsonProperty("aftersale_order_list")
                private List<AftersaleOrderListDTO> aftersaleOrderList;
                @JsonProperty("on_aftersale_order_cnt")
                private Integer onAftersaleOrderCnt;

                @NoArgsConstructor
                @Data
                public static class AftersaleOrderListDTO {
                    @JsonProperty("aftersale_order_id")
                    private Integer aftersaleOrderId;
                }
            }

            @NoArgsConstructor
            @Data
            public static class ExtInfoDTO {
                @JsonProperty("customer_notes")
                private String customerNotes;
                @JsonProperty("merchant_notes")
                private String merchantNotes;
            }
        }
    }
}
