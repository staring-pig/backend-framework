package com.staringpig.framework.wechat.offiaccount.listener.api;

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
}
