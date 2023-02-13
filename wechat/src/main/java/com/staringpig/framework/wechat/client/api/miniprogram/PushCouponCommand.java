package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PushCouponCommand {

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("coupon_id")
    private Long couponId;

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Result extends MiniProgramResult {

    }
}
