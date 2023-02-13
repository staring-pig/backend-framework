package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信小程序code转session命令
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PhoneInfoQuery {

    /**
     * 校验秘钥
     */
    private String sessionKey;
    /**
     * 加密算法的初始向量
     */
    private String iv;
    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    private String encryptedData;

    /**
     * 结果
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends MiniProgramResult {
        /**
         * 用户绑定的手机号（国外手机号会有区号)
         */
        @JsonProperty("phoneNumber")
        private String phoneNumber;
        /**
         * 没有区号的手机号
         */
        @JsonProperty("purePhoneNumber")
        private String purePhoneNumber;
        /**
         * 区号
         */
        @JsonProperty("countryCode")
        private String countryCode;
        @JsonProperty("watermark")
        private WatermarkDTO watermark;
    }

    @NoArgsConstructor
    @Data
    public static class WatermarkDTO {
        @JsonProperty("timestamp")
        private Integer timestamp;
        @JsonProperty("appid")
        private String appid;
    }
}
