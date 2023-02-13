package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlLinkGenerateCommand {

    @JsonProperty("path")
    private String path;

    @JsonProperty("query")
    private String query;

    @JsonProperty("env_version")
    private String envVersion;

    @JsonProperty("is_expire")
    private Boolean isExpire;

    @JsonProperty("expire_type")
    private Integer expireType;

    @JsonProperty("expire_time")
    private Integer expireTime;

    @JsonProperty("expire_interval")
    private Integer expireInterval;

    @JsonProperty("cloud_base")
    private Object cloudBase;

    @Setter
    @Getter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends MiniProgramResult {

        @JsonProperty("url_link")
        private String urlLink;
    }
}
