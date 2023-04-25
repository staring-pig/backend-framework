package com.staringpig.framework.wechat.offiaccount.message.listener.api;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "xml")
public class ReceivedMessageData {

    /**
     * / 开发者微信号
     */
    @JacksonXmlProperty(localName = "ToUserName")
    private String toUserName;
    /**
     * // 发送方帐号（一个OpenID）
     */
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;
    /**
     * // 消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    private long createTime;
    /**
     * // 消息类型（text/image/location/link）
     */
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;
    /**
     * // 消息id，64位整型
     */
    @JacksonXmlProperty(localName = "MsgId")
    private Long msgId;

    @JacksonXmlProperty(localName = "Content")
    private String content;

    @JacksonXmlProperty(localName = "Event")
    private String event;

    @JacksonXmlProperty(localName = "PicUrl")
    private String picUrl;

    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;

    @JacksonXmlProperty(localName = "Ticket")
    private String ticket;

    @JacksonXmlProperty(localName = "Recognition")
    private String recognition;

    @JacksonXmlProperty(localName = "Title")
    private String title;

    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JacksonXmlProperty(localName = "Url")
    private String url;

    @JacksonXmlProperty(localName = "MediaId")
    private String mediaId;

    @JacksonXmlProperty(localName = "ThumbMediaId")
    private String thumbMediaId;

    @JacksonXmlProperty(localName = "Format")
    private String format;

    @JacksonXmlProperty(localName = "Latitude")
    private double latitude;

    @JacksonXmlProperty(localName = "Longitude")
    private double longitude;

    @JacksonXmlProperty(localName = "Precision")
    private double precision;

    @JacksonXmlProperty(localName = "Location_X")
    private double locationX;

    @JacksonXmlProperty(localName = "Location_Y")
    private double locationY;

    @JacksonXmlProperty(localName = "Scale")
    private double scale;

    @JacksonXmlProperty(localName = "Label")
    private String label;
}
