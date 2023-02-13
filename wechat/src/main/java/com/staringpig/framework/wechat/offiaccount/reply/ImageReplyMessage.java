package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.OAMessage;
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

    public ImageReplyMessage(OPAppAccount opAppAccount, Long createTime, String mediaId) {
        super(opAppAccount, createTime, OAMessage.Type.image);
        this.mediaId = mediaId;
    }
}
