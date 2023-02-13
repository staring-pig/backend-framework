package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.event.KeyClickEvent;

import java.util.Optional;

public interface RepayGenerator {

    /**
     * 生成回复消息
     *
     * @param clickKey   点击事件
     * @param opaAccount 账户
     * @return 回复消息
     */
    Optional<ReplyMessage> generate(KeyClickEvent.Key clickKey, OPAppAccount opaAccount);
}
