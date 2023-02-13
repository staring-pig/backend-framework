package com.staringpig.framework.wechat.client.api.miniprogram;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.message.MiniProgramSubscribedMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendSubscribedMessageCommand<T> {

    /**
     * 要发送的用户
     */
    @JsonProperty("touser")
    private String openId;
    /**
     * MiniProgramState 跳转小程序类型
     */
    @JsonProperty("miniprogram_state")
    private MiniProgramSubscribedMessage.MiniProgramState miniProgramState;
    /**
     * 进入小程序查看”的语言类型
     */
    @JsonProperty("lang")
    private String lang = "zh_CN";
    /**
     * 跳转网页时填写
     */
    private String page;
    /**
     * 模板id
     */
    @JsonProperty("template_id")
    private String templateId;
    /**
     * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     */
    private T data;

    @Setter
    @Getter
    public static class Result extends MiniProgramResult {
    }
}
