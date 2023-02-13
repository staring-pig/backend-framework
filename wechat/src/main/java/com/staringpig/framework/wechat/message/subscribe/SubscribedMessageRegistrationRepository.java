package com.staringpig.framework.wechat.message.subscribe;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * "订阅消息（SubscribedMessage）"订阅集仓储层
 *
 * @param <T> 消息订阅集类型
 */
public interface SubscribedMessageRegistrationRepository<T extends SubscribedMessageRegistration> {

    /**
     * 查询用户未发送的票根
     *
     * @param opAppAccountId 用户id
     * @return 用户票根
     */
    Optional<T> queryLastOneNoneSend(Long opAppAccountId);

    /**
     * 查询用户未发送的票根
     *
     * @param opAppAccountId 用户id
     * @param startTime      开始时间
     * @param duration       时间区间
     * @return 用户票根
     */
    Optional<T> queryLastOneNoneSend(Long opAppAccountId, LocalDateTime startTime, Duration duration);

    /**
     * 保存票根
     *
     * @param registration 票根
     */
    void save(T registration);

    /**
     * 跟新票根
     *
     * @param registration 票根
     */
    void update(T registration);

    /**
     * 查询所有订阅该消息的所有账户id
     *
     * @param startTime 开始时间
     * @param duration  时间区间
     * @return 账户ids
     */
    List<Long> fetchAllNoneSendSubscribedAccountIds(LocalDateTime startTime, Duration duration);

    /**
     * 限制查询所有已订阅该消息的账户id
     *
     * @param startTime 开始时间
     * @param duration  时间区间
     * @param limit     限制查询个数
     * @return 限制的账户id
     */
    List<Long> fetchAllNoneSendSubscribedAccountIds(LocalDateTime startTime, Duration duration, Integer limit);
}
