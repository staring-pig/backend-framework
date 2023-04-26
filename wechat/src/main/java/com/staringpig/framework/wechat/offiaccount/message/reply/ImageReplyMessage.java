package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import lombok.Getter;

/**
 * 图片消息
 */
@Getter
public final class ImageReplyMessage extends ReplyMessage {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    private final String mediaId;

    public ImageReplyMessage(OAUser OAUser, Long createTime, String mediaId) {
        super(OAUser, createTime, OAMessage.Type.image);
        this.mediaId = mediaId;
    }
}
