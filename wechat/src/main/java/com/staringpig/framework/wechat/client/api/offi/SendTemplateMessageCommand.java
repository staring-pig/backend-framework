package com.staringpig.framework.wechat.client.api.offi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendTemplateMessageCommand<T> {

    /**
     * 要发送的用户
     */
    @JsonProperty("touser")
    private String openId;
    /**
     * url地址
     */
    private String url;
    /**
     * 要跳转的小程序数据
     */
    @JsonProperty("miniprogram")
    private MiniProgram miniProgram;
    /**
     * 模板id
     */
    @JsonProperty("template_id")
    private String templateId;
    /**
     * 字体颜色
     */
    @JsonProperty("color")
    private String color;

    /**
     * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     */
    private T data;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MiniProgram {
        /**
         * 小程序的appid
         */
        @JsonProperty("appid")
        private String appId;
        /**
         * 小程序要跳转的路径
         */
        @JsonProperty("pagepath")
        private String pagePath;
    }

    @Setter
    @Getter
    public static class Result extends WechatServerResult {
    }
}
