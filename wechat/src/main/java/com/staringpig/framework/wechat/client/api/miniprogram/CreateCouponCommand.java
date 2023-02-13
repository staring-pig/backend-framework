package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.ministore.coupon.PromoteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 创建优惠券
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCouponCommand {

    /**
     * 优惠券类型
     */
    @JsonProperty("type")
    private Long type;
    /**
     * 优惠券名称
     */
    @JsonProperty("name")
    private String name;
    /**
     * 折扣信息相关
     */
    @JsonProperty("discount_info")
    private DiscountInfo discount;
    /**
     * 扩展信息
     */
    @JsonProperty("ext_info")
    private ExtendInfo extend;
    /**
     * 推广相关
     */
    @JsonProperty("promote_info")
    private PromoteInfo promote;
    /**
     * 领用相关
     */
    @JsonProperty("receive_info")
    private ReceiveInfo receive;
    /**
     * 有效期相关
     */
    @JsonProperty("valid_info")
    private ValidInfo valid;

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscountInfo {
        /**
         * 满减金额
         */
        @JsonProperty("discount_fee")
        private BigDecimal discountFee;
        /**
         * 打折商品数量，满减券需填写，换算规则，5000=5折，7000=7折，范围是1000-10000，必须是100的整数
         */
        @JsonProperty("discount_num")
        private Long discountNum;
        /**
         * 条件
         */
        @JsonProperty("discount_condition")
        private ConditionInfo condition;
    }

    /**
     * 打折条件
     */
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConditionInfo {
        /**
         * 商品折扣券打折金额
         */
        @JsonProperty("product_cnt")
        private BigDecimal productCnt;
        /**
         * 商品id，商品折扣券需填写
         */
        @JsonProperty("product_ids")
        private Set<Long> productIds;
        /**
         * 商品价格，满减券需填写
         */
        @JsonProperty("product_price")
        private BigDecimal productPrice;
    }

    /**
     * 扩展信息
     */
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExtendInfo {
        /**
         * 优惠券失效时间戳
         */
        @JsonProperty("invalid_time")
        private Long invalidTime;
        /**
         * 商品折扣券领取后跳转的商品id
         */
        @JsonProperty("jump_product_id")
        private Long jumpProductId;
        /**
         * 备注信息
         */
        @JsonProperty("notes")
        private String notes;
        /**
         * 优惠券有效时间戳
         */
        @JsonProperty("valid_time")
        private Long validTime;
    }

    /**
     * 推广相关
     */
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PromoteInfo {
        /**
         * 推广类型
         */
        @JsonProperty("promote_type")
        private PromoteType promoteType;
        /**
         * 自定义渠道
         */
        @JsonProperty("customize_channel")
        private String customizeChannel;
    }

    /**
     * 接收相关
     */
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReceiveInfo {
        /**
         * 优惠券领用开始时间戳
         */
        @JsonProperty("start_time")
        private Long startTime;
        /**
         * 优惠券领用结束时间戳
         */
        @JsonProperty("end_time")
        private Long endTime;
        /**
         * 优惠券领用总数
         */
        @JsonProperty("total_num")
        private Long totalNum;
        /**
         * 单人限领张数
         */
        @JsonProperty("limit_num_one_person")
        private Long limitNumOnePerson;
    }

    /**
     * 有效期相关
     */
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ValidInfo {
        /**
         * 优惠券有效期类型
         */
        @JsonProperty("valid_type")
        private Integer validType;
        /**
         * 优惠券有效期天数，和start_time二选一
         */
        @JsonProperty("valid_day_num")
        private Integer validDayNum;
        /**
         * 优惠券有效期结束时间，若填了start必填
         */
        @JsonProperty("start_time")
        private Long startTime;
        /**
         * 优惠券有效期开始时间，和valid_day_num二选一
         */
        @JsonProperty("end_time")
        private Long endTime;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends MiniProgramResult {

        /**
         * 返回数据
         */
        @JsonProperty("session_key")
        private ResultData data;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResultData {
        /**
         * 优惠券id
         */
        @JsonProperty("session_key")
        private Long couponId;
    }
}
