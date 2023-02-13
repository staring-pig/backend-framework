package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ldh
 * time 2021-11-22 18:07
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GenerateUrlLinkCommand {
    /**
     * 通过 URL Link 进入的小程序页面路径，
     * 必须是已经发布的小程序存在的页面，不可携带 query 。
     * path 为空时会跳转小程序主页
     */
    @JsonProperty("path")
    private String path;
    /**
     * 通过 URL Link 进入小程序时的query，最大1024个字符，只支持数字，大小写英文
     * 以及部分特殊字符：!#$&'()*+,/:;=?@-._~%
     */
    @JsonProperty("query")
    private String query;
    /**
     * 生成的 URL Link 类型，到期失效：true，永久有效：false。
     * 注意，永久有效 Link 和有效时间超过180天的到期失效 Link 的总数上限为10万个，
     * 详见获取 URL Link，生成 Link 前请仔细确认。
     */
    @JsonProperty("is_expire")
    private Boolean isExpire = false;
    /**
     * 小程序 URL Link 失效类型，失效时间：0，失效间隔天数：1
     */
    @JsonProperty("expire_type")
    private Integer expireType = 0;
    /**
     * 到期失效的URL Link的失效间隔天数。
     * 生成的到期失效URL Link在该间隔时间到达前有效。
     * 最长间隔天数为365天。expire_type 为 1 必填
     */
    @JsonProperty("expire_interval")
    private Integer expireInterval;
    /**
     * 到期失效的 URL Link 的失效时间，为 Unix 时间戳。
     * 生成的到期失效 URL Link 在该时间前有效。最长有效期为1年。
     * expire_type 为 0 必填
     */
    @JsonProperty("expire_time")
    private Long expireTime;
    /**
     * 要打开的小程序版本。
     * 正式版为 "release"，体验版为"trial"，开发版为"develop"，
     * 仅在微信外打开时生效。
     */
    @JsonProperty("env_version")
    private String envVersion = "release";
    /**
     * 云开发静态网站自定义 H5 配置参数，
     * 可配置中转的云开发 H5 页面。不填默认用官方 H5 页面
     */
    @JsonProperty("cloud_base")
    private CloudBaseDTO cloudBase;

    @NoArgsConstructor
    @Data
    /**
     * 暂不考虑云开发参数
     */
    public static class CloudBaseDTO {
        @JsonProperty("env")
        private String env;
        @JsonProperty("domain")
        private String domain;
        @JsonProperty("path")
        private String path;
        @JsonProperty("query")
        private String query;
    }

    @NoArgsConstructor
    @Data
    public static class Result extends WechatServerResult {
        /**
         * 生成的小程序 URL Link
         */
        @JsonProperty("url_link")
        private String urlLink;
    }
}
