package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
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

    public VoiceReplyMessage(OAUser OAUser, Long createTime, String mediaId) {
        super(OAUser, createTime, Type.voice);
        this.mediaId = mediaId;
    }
}
