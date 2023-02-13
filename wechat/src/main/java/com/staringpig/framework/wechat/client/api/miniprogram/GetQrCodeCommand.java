package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 微信小程序生成永久有效的小程序码命令
 *
 * @param <T>
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetQrCodeCommand<T> {

    /**
     * 扫码进入的小程序页面路径，最大长度 128 字节，不能为空；对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"，即可在 wx.getLaunchOptionsSync 接口中的 query 参数获取到 {foo:"bar"}。
     */
    @JsonProperty("path")
    private String path;

    /**
     * 二维码的宽度，单位 px，最小 280px，最大 1280px
     */
    @JsonProperty("width")
    private Integer width;
    /**
     * 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false
     */
    @JsonProperty("auto_color")
    private Boolean autoColor = false;
    /**
     * auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示
     */
    @JsonProperty("line_color")
    private T lineColor;
    /**
     * 是否需要透明底色，为 true 时，生成透明底色的小程序
     */
    @JsonProperty("is_hyaline")
    private Boolean isHyaline;

    @Setter
    @Getter
    public static class Result extends MiniProgramResult {
        /**
         * 图片格式
         */
        @JsonProperty("contentType")
        private String contentType;
        /**
         * 图片数据
         */
        @JsonProperty("buffer")
        private String buffer;
    }
}
