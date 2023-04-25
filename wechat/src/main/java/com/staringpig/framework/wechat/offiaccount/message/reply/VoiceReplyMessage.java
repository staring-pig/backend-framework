package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 语音消息
 */
@Getter
public final class VoiceReplyMessage extends ReplyMessage {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    private final String mediaId;

    public VoiceReplyMessage(OAAccount oaAccount, Long createTime, String mediaId) {
        super(oaAccount, createTime, Type.voice);
        this.mediaId = mediaId;
    }
}
