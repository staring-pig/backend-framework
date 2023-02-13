package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;
import net.dreamlu.mica.core.utils.StringPool;

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

    public MusicReplyMessage(OPAppAccount opAppAccount, Long createTime, String thumbMediaId) {
        super(opAppAccount, createTime, Type.music);
        this.thumbMediaId = thumbMediaId;
        this.musicURL = StringPool.EMPTY;
        this.title = StringPool.EMPTY;
        this.description = StringPool.EMPTY;
        this.hqMusicUrl = StringPool.EMPTY;
    }

    public MusicReplyMessage(OPAppAccount opAppAccount, Long createTime, String thumbMediaId,
                             String musicURL, String title, String description, String hqMusicUrl) {
        super(opAppAccount, createTime, Type.music);
        this.thumbMediaId = thumbMediaId;
        this.musicURL = musicURL;
        this.title = title;
        this.description = description;
        this.hqMusicUrl = hqMusicUrl;
    }
}
