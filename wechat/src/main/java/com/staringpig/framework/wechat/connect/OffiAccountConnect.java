package com.staringpig.framework.wechat.connect;

import com.staringpig.framework.wechat.OPApplication;
import com.staringpig.framework.wechat.account.OPAccount;
import com.staringpig.framework.wechat.connect.event.FollowEvent;
import com.staringpig.framework.wechat.connect.event.SceneFollowEvent;
import com.staringpig.framework.wechat.connect.event.reply.EventReply;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * 微信公众号对外提供的链接服务
 */
public interface OffiAccountConnect {

    /**
     * 订阅关注公众号事件
     *
     * @param onEvent 当事件发生时回调
     */
    void subscribeFollow(Function<FollowEvent, Optional<EventReply>> onEvent);

    /**
     * 订阅场景关注公众号事件
     *
     * @param sceneKey 场景key值
     * @param onEvent  当事件发生时回调
     */
    void subscribeSceneFollow(String sceneKey, Function<SceneFollowEvent, Optional<EventReply>> onEvent);

    /**
     * 某个账号是否关注了公众号
     *
     * @param opAccount 公众平台账号
     * @return 是|否
     */
    boolean hasFollow(OPAccount opAccount);

    /**
     * 关注事件通知
     *
     * @param event 事件
     */
    List<EventReply> notifyFollowEvent(FollowEvent event);

    /**
     * 扫码关注事件通知
     *
     * @param event 事件
     * @return 结果
     */
    Optional<EventReply> notifySceneFollowEvent(SceneFollowEvent event);

    /**
     * 发送模板消息
     *
     * @param messageTemplateId 消息模板id
     * @param opAccount         用户账户
     * @param data              数据
     * @param <T>               数据的类型
     */
    <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, T data);

    /**
     * 发送模板消息
     *
     * @param messageTemplateId 消息模板id
     * @param opAccount         用户账户
     * @param data              数据
     * @param <T>               数据的类型
     * @param color             消息字体颜色
     */
    <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, T data, String color);

    /**
     * 发送模板消息
     *
     * @param messageTemplateId 消息模板id
     * @param opAccount         用户账户
     * @param data              数据
     * @param url               要跳转的外部路径
     * @param <T>               数据的类型
     */
    <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, String url, T data);

    /**
     * 发送模板消息
     *
     * @param messageTemplateId 消息模板id
     * @param opAccount         用户账户
     * @param data              数据
     * @param url               要跳转的外部路径
     * @param <T>               数据的类型
     * @param color             字体颜色
     */
    <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, String url, T data, String color);

    /**
     * 发送模板消息
     *
     * @param messageTemplateId 消息模板id
     * @param opAccount         用户账户
     * @param data              数据
     * @param <T>               数据的类型
     * @param path              要跳转的小程序路径
     * @param toMiniProgram     要跳转的小程序
     */
    <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, OPApplication toMiniProgram,
                                 String path, T data);

    /**
     * 发送模板消息
     *
     * @param messageTemplateId 消息模板id
     * @param opAccount         用户账户
     * @param data              数据
     * @param <T>               数据的类型
     * @param path              要跳转的小程序路径
     * @param toMiniProgram     要跳转的小程序
     * @param color             字体颜色
     */
    <T> void sendTemplateMessage(String messageTemplateId, OPAccount opAccount, OPApplication toMiniProgram,
                                 String path, T data, String color);
}
