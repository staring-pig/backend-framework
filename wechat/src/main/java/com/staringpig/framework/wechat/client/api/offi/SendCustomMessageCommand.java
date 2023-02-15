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
public class SendCustomMessageCommand {

    /**
     * 要发送的用户
     */
    @JsonProperty("touser")
    private String openId;
    /**
     * 要跳转的小程序数据
     */
    @JsonProperty("msgtype")
    private String messageType = "text";

    /**
     * 文本
     */
    private Text text;

    /**
     * 文本内容
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Text {
        private String content;
    }

    @Setter
    @Getter
    public static class Result extends WechatServerResult {
    }
}
