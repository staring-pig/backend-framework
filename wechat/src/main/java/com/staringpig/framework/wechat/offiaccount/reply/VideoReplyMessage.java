package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 视频消息
 */
@Getter
public final class VideoReplyMessage extends ReplyMessage {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    private final String mediaId;
    /**
     * 视频消息的标题
     */
    private final String title;
    /**
     * 视频消息的描述
     */
    private final String description;

    public VideoReplyMessage(OPAppAccount opAppAccount, Long createTime, String mediaId, String title,
                             String description) {
        super(opAppAccount, createTime, Type.video);
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }
}
