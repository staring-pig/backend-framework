package com.staringpig.framework.wechat.offiaccount.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.ApiResponse;
import lombok.Getter;
import lombok.Setter;

public class UploadVoiceCommand {

    @Setter
    @Getter
    public static class Result extends ApiResponse {
        /**
         * 媒体ID
         */
        @JsonProperty("media_id")
        private String mediaId;
        /**
         * 类型
         */
        private String type;
        /**
         * 创建时间
         */
        @JsonProperty("created_at")
        private Long createdAt;
    }
}
