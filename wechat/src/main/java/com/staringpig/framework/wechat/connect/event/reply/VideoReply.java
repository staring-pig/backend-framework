package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

/**
 * 视频消息
 */
@Getter
public class VideoReply extends EventReply {

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

    public VideoReply(String mediaId, String title, String description) {
        this.mediaId = mediaId;
        this.title = title;
        this.description = description;
    }
}
