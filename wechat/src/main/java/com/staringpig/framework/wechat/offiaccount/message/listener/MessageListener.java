package com.staringpig.framework.wechat.offiaccount.message.listener;

import com.staringpig.framework.wechat.offiaccount.message.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.ReceivedMessageData;

import java.util.Optional;

/**
 * 消息监听器
 */
public interface MessageListener {

    String MESSAGE_RECEIVE_PATH = "/receive";

    /**
     * 当接收到消息时
     */
    Optional<PublishedMessageData> on(ReceivedMessageData message);

    /**
     * 校验微信安全信息
     *
     * @param signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     * @param timestamp 时间戳
     * @param nonce     随机数
     */
    boolean checkSignature(String signature, String timestamp, String nonce);
}
