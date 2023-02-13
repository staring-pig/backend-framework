package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.offiaccount.event.KeyClickEvent;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface KeyRepayMessageRepository {

    /**
     * 查询单个
     */
    Optional<KeyRepayMessage> query(KeyClickEvent.Key key);

    /**
     * 查询map
     */
    Map<KeyClickEvent.Key, KeyRepayMessage> queryMap(Collection<KeyClickEvent.Key> keys);
}
