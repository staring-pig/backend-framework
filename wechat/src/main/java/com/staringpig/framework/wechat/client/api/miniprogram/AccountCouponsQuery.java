package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AccountCouponsQuery {

    /**
     * openId
     */
    @JsonProperty("openid")
    private String openId;
    /**
     * 状态
     */
    @JsonProperty("status")
    private Integer status;

    public AccountCouponsQuery(String openId) {
        this.openId = openId;
    }

    public AccountCouponsQuery(String openId, Status status) {
        this.openId = openId;
        this.status = status.code;
    }

    /**
     * 结果
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends MiniProgramResult {

        @JsonProperty("user_coupon_list")
        private List<CouponInfo> userCouponList;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CouponInfo {
        @JsonProperty("coupon_id")
        private Long couponId;
        @JsonProperty("status")
        private String status;
        @JsonProperty("create_time")
        private String createTime;
        @JsonProperty("update_time")
        private String updateTIme;
        @JsonProperty("start_time")
        private String startTime;
        @JsonProperty("end_time")
        private String endTime;
        @JsonProperty("order_id")
        private Long orderId;
        @JsonProperty("discount_fee")
        private BigDecimal discountFee;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        /**
         * 生效中
         */
        COUPON_USER_STATUS_VALID(100),
        /**
         * 已过期
         */
        COUPON_USER_STATUS_EXPIRED(101),
        /**
         * 已使用
         */
        COUPON_USER_STATUS_USED(102);

        private Integer code;
    }
}
