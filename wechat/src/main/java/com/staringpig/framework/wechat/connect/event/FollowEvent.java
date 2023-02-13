package com.staringpig.framework.wechat.connect.event;

import com.staringpig.framework.wechat.account.OPAccount;

/**
 * 关注(公众号)事件
 */
public class FollowEvent extends Event {

    public FollowEvent(OPAccount opAccount, Long timestamp) {
        super(opAccount, timestamp);
    }
}
