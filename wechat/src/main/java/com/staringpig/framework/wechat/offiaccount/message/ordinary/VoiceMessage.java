package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.support.AllInOne;
import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import lombok.Getter;

/**
 * 语音消息
 */
@Getter
public final class VoiceMessage extends OrdinaryMessage {

    /**
     * 语音消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private final String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    private final Format format;

    /**
     * 语音识别结果，UTF8编码
     * <p>
     * 请注意，开通语音识别后，用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recognition字段
     * （注：由于客户端缓存，开发者开启或者关闭语音识别功能，对新关注者立刻生效，对已关注用户需要24小时生效。开发者可以重新关注此帐号进行测试）。
     */
    private final String recognition;

    public VoiceMessage(String id, OAUser OAUser, Long createTime, String mediaId, Format format) {
        super(id, OAUser, createTime, Type.voice);
        this.mediaId = mediaId;
        this.format = format;
        this.recognition = AllInOne.STRING_EMPTY;
    }

    public VoiceMessage(String id, OAUser OAUser, Long createTime, String mediaId, Format format,
                        String recognition) {
        super(id, OAUser, createTime, Type.voice);
        this.mediaId = mediaId;
        this.format = format;
        this.recognition = recognition;
    }

    public enum Format {
        amr,
        speex
    }
}
