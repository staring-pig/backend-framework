package com.staringpig.framework.wechat.offiaccount.message.handler;

import com.staringpig.framework.wechat.offiaccount.message.ordinary.VoiceMessage;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;

import java.math.BigDecimal;
import java.util.Optional;

public interface AllInOneHandler {

    /**
     * 扫码订阅监听
     */
    Optional<ReplyMessage> onScanSubscribed(OAUser OAUser, String eventKey, String ticket);

    /**
     * 扫码订阅监听
     */
    Optional<ReplyMessage> onScan(OAUser OAUser, String eventKey, String ticket);

    /**
     * 订阅监听
     */
    Optional<ReplyMessage> onSubscribed(OAUser OAUser);

    /**
     * 取消订阅
     */
    Optional<ReplyMessage> onUnSubscribed(OAUser OAUser);

    /**
     * 上传地址事件
     */
    Optional<ReplyMessage> onUpLocation(OAUser oaUser, BigDecimal latitude, BigDecimal longitude, BigDecimal precision);

    /**
     * 点击事件，查看地址
     */
    Optional<ReplyMessage> onView(OAUser oaUser, String url);

    /**
     * 接受到图片
     */
    Optional<ReplyMessage> receiveImage(OAUser oaUser, String picUrl, String mediaId);

    /**
     * 接收到链接
     */
    Optional<ReplyMessage> receiveLink(OAUser oaUser, String title, String description, String url);

    /**
     * 收到地址
     */
    Optional<ReplyMessage> receiveLocation(OAUser oaUser, BigDecimal locationX, BigDecimal locationY, BigDecimal scale,
                                           String label);

    /**
     * 收到短视频
     */
    Optional<ReplyMessage> receiveShortVideo(OAUser oaUser, String mediaId, String thumbMediaId);

    /**
     * 接收到文本
     */
    Optional<ReplyMessage> receiveText(OAUser oaUser, String content);

    /**
     * 接收到视频
     */
    Optional<ReplyMessage> receiveVideo(OAUser oaUser, String mediaId, String thumbMediaId);

    /**
     * 收到语音
     */
    Optional<ReplyMessage> receiveVoice(OAUser oaUser, String mediaId, VoiceMessage.Format format, String recognition);
}
