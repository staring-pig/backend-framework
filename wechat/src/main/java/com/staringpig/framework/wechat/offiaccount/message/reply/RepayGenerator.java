package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import com.staringpig.framework.wechat.offiaccount.message.event.KeyClickEvent;

import java.util.Optional;

public interface RepayGenerator {

    /**
     * 生成回复消息
     *
     * @param clickKey   点击事件
     * @param opaAccount 账户
     * @return 回复消息
     */
    Optional<ReplyMessage> generate(KeyClickEvent.Key clickKey, OAAccount oaAccount);
}
