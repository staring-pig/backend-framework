package com.staringpig.framework.wechat.ministore.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 订单订购的产品
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    /**
     * 小商店内部商品ID
     */
    private Long productId;
    /**
     * 小商店内部skuID
     */
    private Long skuId;
    /**
     * sku小图
     */
    private String thumbImg;
    /**
     * sku数量
     */
    private Long skuCnt;
    /**
     * 正在售后/退款流程中的sku数量
     */
    private Long onAfterSaleSkuCnt;
    /**
     * 完成售后/退款的sku数量
     */
    private Long finishAfterSaleSkuCnt;
    /**
     * 售卖价格（单位：分）
     */
    private BigDecimal salePrice;
}
