package com.staringpig.framework.wechat.client.api.offi;

import com.staringpig.framework.wechat.client.api.WechatServerResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ldh
 * time 2021-12-1 11:08
 */
public class MenuAdd {

    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    public static class Result extends WechatServerResult {

    }
}
