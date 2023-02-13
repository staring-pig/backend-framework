package com.staringpig.framework.wechat.ministore.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 订单价格
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderPrice {
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 订单价格
     */
    private BigDecimal orderPrice;
    /**
     * 运费
     */
    private BigDecimal freight;
    /**
     * 是否有折扣
     */
    private Boolean hasDiscounted;
    /**
     * 折扣金额
     */
    private BigDecimal discountedPrice;
}
