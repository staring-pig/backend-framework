package com.staringpig.framework.wechat.offiaccount.message.listener;

import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.PublishedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.listener.api.ReceivedMessageData;
import com.staringpig.framework.wechat.offiaccount.message.reply.ReplyMessage;

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
