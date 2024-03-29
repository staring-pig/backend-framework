package com.staringpig.framework.wechat.offiaccount.reply;

import com.staringpig.framework.wechat.account.OPAppAccount;
import com.staringpig.framework.wechat.offiaccount.event.KeyClickEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

/**
 * key 回复消息模板
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyRepayMessage {

    /**
     * 消息类型
     */
    private Type type;
    /**
     * key
     */
    private KeyClickEvent.Key key;
    /**
     * 多种类型消息共用的mediaId
     */
    private String mediaId;
    /**
     * 图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
     */
    private List<NewsReplyMessage.Item> articles;
    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     */
    private String content;
    /**
     * 音乐链接
     */
    private String musicURL;
    /**
     * 音乐标题
     */
    private String title;
    /**
     * 音乐描述
     */
    private String description;
    /**
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     */
    private String hqMusicUrl;
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    private String thumbMediaId;

    public enum Type {
        IMAGE,

        MUSIC,

        NEWS,

        TEXT,

        VIDEO,

        VOICE
    }

    /**
     * 生成回复消息
     *
     * @param opAppAccount 微信账户
     * @return 回复消息
     */
    public ReplyMessage generate(OPAppAccount opAppAccount) {

        ReplyMessage replyMessage = null;

        switch (this.type) {
            case NEWS:
                replyMessage = new NewsReplyMessage(opAppAccount, Instant.now().getEpochSecond(), this.articles);
                break;
            case TEXT:
                replyMessage = new TextReplyMessage(opAppAccount, Instant.now().getEpochSecond(), this.content);
                break;
            case IMAGE:
                replyMessage = new ImageReplyMessage(opAppAccount, Instant.now().getEpochSecond(), this.mediaId);
                break;
            case MUSIC:
                replyMessage = new MusicReplyMessage(opAppAccount, Instant.now().getEpochSecond(), this.thumbMediaId);
                break;
            case VIDEO:
                replyMessage = new VideoReplyMessage(opAppAccount, Instant.now().getEpochSecond(), this.mediaId,
                        this.title, this.description);
                break;
            case VOICE:
                replyMessage = new VoiceReplyMessage(opAppAccount, Instant.now().getEpochSecond(), this.mediaId);
                break;
        }

        return replyMessage;
    }
}
