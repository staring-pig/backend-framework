package com.staringpig.framework.wechat.connect.event.reply;

import lombok.Getter;

/**
 * 音乐
 */
@Getter
public class MusicReply extends EventReply {

    /**
     * 音乐链接
     */
    private final String musicURL;
    /**
     * 音乐标题
     */
    private final String title;
    /**
     * 音乐描述
     */
    private final String description;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private final String hqMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private final String thumbMediaId;

    public MusicReply(String musicURL, String title, String description, String hqMusicUrl, String thumbMediaId) {
        this.musicURL = musicURL;
        this.title = title;
        this.description = description;
        this.hqMusicUrl = hqMusicUrl;
        this.thumbMediaId = thumbMediaId;
    }
}
