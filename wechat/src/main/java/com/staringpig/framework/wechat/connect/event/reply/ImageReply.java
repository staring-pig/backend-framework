package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

/**
 * 图片回复
 */
public class ImageReply extends EventReply {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    @Getter
    private final String mediaId;

    public ImageReply(String mediaId) {
        this.mediaId = mediaId;
    }
}
