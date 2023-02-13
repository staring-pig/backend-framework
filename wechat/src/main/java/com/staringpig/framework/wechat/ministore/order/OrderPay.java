package com.staringpig.framework.wechat.ministore.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 订单支付
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderPay {

    /**
     * 支付方式（目前只有"微信支付"）
     */
    private PayMethod payMethod;
    /**
     * 预支付id
     */
    private String prePayId;
    /**
     * 支付订单号
     */
    private String transactionId;
    /**
     * 预付款时间
     */
    private LocalDateTime prePayTime;
    /**
     * 付款时间
     */
    private LocalDateTime payTime;

    /**
     * 支付方式
     */
    public enum PayMethod {
        /**
         * 微信支付
         */
        WECHAT_PAY,
    }
}
