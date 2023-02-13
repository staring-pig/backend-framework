package com.staringpig.framework.wechat.ministore.coupon;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 打折信息
 */
public class Discount {
    /**
     * 满减金额
     */
    private BigDecimal discountFee;
    /**
     * 打折商品数量，满减券需填写，换算规则，5000=5折，7000=7折，范围是1000-10000，必须是100的整数
     */
    private Long discountNum;
    /**
     * 条件
     */
    private Condition condition;

    /**
     * 打折条件
     */
    public static class Condition {
        /**
         * 商品折扣券打折金额
         */
        private BigDecimal productCnt;
        /**
         * 商品id，商品折扣券需填写
         */
        private Set<Long> productIds;
        /**
         * 商品价格，满减券需填写
         */
        private BigDecimal productPrice;
    }
}

