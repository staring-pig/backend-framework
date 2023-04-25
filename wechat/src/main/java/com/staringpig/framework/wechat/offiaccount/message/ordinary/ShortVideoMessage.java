package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

/**
 * 视频消息
 */
@Getter
public final class ShortVideoMessage extends OrdinaryMessage {

    /**
     * 视频消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private final String mediaId;
    /**
     * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    private final String thumbMediaId;

    public ShortVideoMessage(String id, OAAccount oaAccount, Long createTime, String mediaId,
                             String thumbMediaId) {
        super(id, oaAccount, createTime, Type.shortvideo);
        this.mediaId = mediaId;
        this.thumbMediaId = thumbMediaId;
    }
}
