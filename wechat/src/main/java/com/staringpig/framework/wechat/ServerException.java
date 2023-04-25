package com.staringpig.framework.wechat;

/**
 * code is far away from bug with the animal protecting
 *
 * @author Isaac
 * @description 微信回调的自定义异常
 * @date 2021/7/19 13:07
 */
public class ServerException extends RuntimeException {

    public ServerException(String message) {
        super(message);
    }
}
