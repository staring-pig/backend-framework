package com.staringpig.framework.wechat.offiaccount.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.ApiResponse;
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
public class SendCustomVoiceMessageCommand {

    /**
     * 要发送的用户
     */
    @JsonProperty("touser")
    private String openId;
    /**
     * 要跳转的小程序数据
     */
    @JsonProperty("msgtype")
    private String messageType = "voice";

    /**
     * 文本
     */
    private Voice voice;

    /**
     * 文本内容
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Voice {
        @JsonProperty("media_id")
        private String mediaId;
    }

    @Setter
    @Getter
    public static class Result extends ApiResponse {
    }
}
