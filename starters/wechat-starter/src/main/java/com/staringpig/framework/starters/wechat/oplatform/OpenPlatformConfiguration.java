package com.staringpig.framework.starters.wechat.oplatform;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration(proxyBeanMethods = false)
public class OpenPlatformConfiguration {

    @EnableJpaRepositories("com.staringpig.framework.starters.wechat.oplatform")
    @Configuration(proxyBeanMethods = false)
    public static class RepositoryConfiguration {
    }

}
