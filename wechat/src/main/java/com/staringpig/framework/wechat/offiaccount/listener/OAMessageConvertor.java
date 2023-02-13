package com.staringpig.framework.wechat.offiaccount.listener;

import com.staringpig.framework.wechat.offiaccount.OAMessage;
import com.staringpig.framework.wechat.offiaccount.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.listener.api.ReceivedMessageData;
import com.staringpig.framework.wechat.offiaccount.reply.ReplyMessage;

public interface OAMessageConvertor {

    /**
     * 从 data 转到 com.staringpig.backendframework.message
     */
    OAMessage convert(ReceivedMessageData messageData);

    /**
     * 从message转到 data
     */
    PublishedMessageData convert(ReplyMessage replyMessage);
}
