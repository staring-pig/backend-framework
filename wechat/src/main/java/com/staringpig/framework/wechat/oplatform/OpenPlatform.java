package com.staringpig.framework.wechat.oplatform;

import lombok.Getter;

/**
 * 开放平台
 */
public abstract class OpenPlatform {

    /**
     * 开放平台也有自己的ID
     */
    @Getter
    private final String appId;

    public OpenPlatform(String appId) {
        this.appId = appId;
    }
}
