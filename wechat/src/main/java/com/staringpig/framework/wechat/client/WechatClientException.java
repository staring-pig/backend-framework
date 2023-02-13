package com.staringpig.framework.wechat.client;

/**
 * code is far away from bug with the animal protecting
 *
 * @author Isaac
 * @description 发起请求微信的自定义异常
 * @date 2021/7/19 11:55
 */
public class WechatClientException extends RuntimeException {

    public WechatClientException(String message) {
        super(message);
    }
}
