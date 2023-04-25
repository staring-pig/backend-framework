package com.staringpig.framework.starters.ai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "staring-pig.framework.ai.open-ai")
public class OpenAIProperties {

    /**
     * open-ai token
     */
    private String token;
    /**
     * url
     */
    private String baseUrl;
    /**
     * 工具函数的地址
     */
    private String utilsBaseUrl;
    /**
     * 代理配置
     */
    private Proxy proxy;
    /**
     * 链接池配置
     */
    private ConnectionPool connectionPool;
    /**
     * 读取超时时间
     */
    private Duration readTimeout;
    /**
     * 链接超时
     */
    private Duration connectTimeout;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Proxy {
        /**
         * 代理类型
         */
        private java.net.Proxy.Type type;
        /**
         * 代理
         */
        private String hostname;
        /**
         * 代理
         */
        private Integer port;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConnectionPool {

        private int maxIdleConnections;

        private long keepAliveDuration;

        private TimeUnit timeUnit;
    }

}
