package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

/**
 * 声音回复
 */
@Getter
public class VoiceReply extends EventReply {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     */
    private final String mediaId;

    public VoiceReply(String mediaId) {
        this.mediaId = mediaId;
    }
}
