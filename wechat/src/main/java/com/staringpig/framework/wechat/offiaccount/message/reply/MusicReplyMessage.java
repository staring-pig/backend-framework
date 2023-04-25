package com.staringpig.framework.wechat.offiaccount.message.reply;

import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 图片消息
 */
@Getter
public final class MusicReplyMessage extends ReplyMessage {

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

    public MusicReplyMessage(OAAccount oaAccount, Long createTime, String thumbMediaId) {
        super(oaAccount, createTime, Type.music);
        this.thumbMediaId = thumbMediaId;
        this.musicURL = AllInOne.STRING_EMPTY;
        this.title = AllInOne.STRING_EMPTY;
        this.description = AllInOne.STRING_EMPTY;
        this.hqMusicUrl = AllInOne.STRING_EMPTY;
    }

    public MusicReplyMessage(OAAccount oaAccount, Long createTime, String thumbMediaId,
                             String musicURL, String title, String description, String hqMusicUrl) {
        super(oaAccount, createTime, Type.music);
        this.thumbMediaId = thumbMediaId;
        this.musicURL = musicURL;
        this.title = title;
        this.description = description;
        this.hqMusicUrl = hqMusicUrl;
    }
}
