package com.staringpig.framework.wechat.offiaccount.listener.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "xml")
public class PublishedMessageData {

    /**
     * 接收方帐号（收到的OpenID）
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;
    /**
     * // 开发者微信号
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;
    /**
     * // 消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    private long createTime;
    /**
     * // 消息类型（text/music/news）
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;
    /**
     * // 位0x0001被标志时，星标刚收到的消息
     */
    @JacksonXmlProperty(localName = "FuncFlag")
    private int funcFlag;
    /**
     * // 图文消息个数，限制为10条以内
     */
    @JacksonXmlProperty(localName = "ArticleCount")
    private int articleCount;
    /**
     * // 多条图文消息信息，默认第一个item为大图
     */
    @JacksonXmlProperty(localName = "Articles")
    private List<Article> articles;

    /**
     * 音乐
     */
    @JacksonXmlProperty(localName = "Music")
    private Music music;
    /**
     * 图片
     */
    @JacksonXmlProperty(localName = "Image")
    private Image image;
    /**
     * / 消息内容
     */
    @JacksonXmlProperty(localName = "Content")
    private String content;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Article {
        // 图文消息名称
        @JacksonXmlProperty(localName = "Title")
        private String title;
        // 图文消息描述
        @JacksonXmlProperty(localName = "Description")
        private String description;
        // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
        @JacksonXmlProperty(localName = "PicUrl")
        private String picUrl;
        // 点击图文消息跳转链接
        @JacksonXmlProperty(localName = "Url")
        private String url;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Music {
        // 音乐名称
        @JacksonXmlProperty(localName = "Title")
        private String title;
        // 音乐描述
        @JacksonXmlProperty(localName = "Description")
        private String description;
        // 音乐链接
        @JacksonXmlProperty(localName = "MusicUrl")
        private String musicUrl;
        // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
        @JacksonXmlProperty(localName = "HQMusicUrl")
        private String hqMusicUrl;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Image {
        @JacksonXmlProperty(localName = "MediaId")
        private String mediaId;
    }
}
