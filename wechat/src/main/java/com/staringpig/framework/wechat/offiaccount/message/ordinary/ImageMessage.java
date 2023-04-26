package com.staringpig.framework.wechat.offiaccount.message.ordinary;

import com.staringpig.framework.wechat.offiaccount.user.OAUser;
import com.staringpig.framework.wechat.offiaccount.message.OAMessage;
import lombok.Getter;

/**
 * 图片消息
 */
@Getter
public final class ImageMessage extends OrdinaryMessage {

    /**
     * 图片链接（由系统生成）
     */
    private final String picUrl;
    /**
     * 图片消息媒体id，可以调用获取临时素材接口拉取数据。
     */
    private final String mediaId;

    public ImageMessage(String id, OAUser OAUser, Long createTime, String picUrl, String mediaId) {
        super(id, OAUser, createTime, OAMessage.Type.image);
        this.picUrl = picUrl;
        this.mediaId = mediaId;
    }
}
