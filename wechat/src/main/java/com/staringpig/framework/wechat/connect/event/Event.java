package com.staringpig.framework.wechat.connect.event;

import com.staringpig.framework.wechat.account.OPAccount;
import lombok.Getter;

/**
 * 事件类型
 */
@Getter
public abstract class Event {

    /**
     * 平台账号
     */
    private final OPAccount opAccount;
    /**
     * 事件发生事件
     */
    private final Long timestamp;

    protected Event(OPAccount opAccount, Long timestamp) {
        this.opAccount = opAccount;
        this.timestamp = timestamp;
    }
}
