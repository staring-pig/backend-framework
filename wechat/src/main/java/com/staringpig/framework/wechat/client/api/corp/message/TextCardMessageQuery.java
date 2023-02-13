package com.staringpig.framework.wechat.client.api.corp.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.api.corp.CorpResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TextCardMessageQuery {

    @JsonProperty(value = "touser")
    private String toUser;

    @JsonProperty(value = "toparty")
    private String toParty;

    @JsonProperty(value = "totag")
    private String toTag;

    @JsonProperty(value = "msgtype")
    private String msgType;

    @JsonProperty(value = "agentid")
    private Long agentId;

    @JsonProperty(value = "textcard")
    private TextCard textCard;

    @JsonProperty(value = "enable_id_trans")
    private String enableIdTrans;

    @JsonProperty(value = "enable_duplicate_check")
    private String enableDuplicateCheck;

    @JsonProperty(value = "duplicate_check_interval")
    private String duplicateCheckInterval;


    @Data
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TextCard {

        @JsonProperty(value = "title")
        private String title;

        @JsonProperty(value = "description")
        private String description;

        @JsonProperty(value = "url")
        private String url;

        @JsonProperty(value = "btntxt")
        private String btnTxt;
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Result extends CorpResult {

        /**
         * 不合法的userid，不区分大小写，统一转为小写
         */
        private String invaliduser;
        /**
         * 不合法的partyid
         */
        private String invalidparty;
        /**
         * 不合法的标签id
         */
        private String invalidtag;
        /**
         * 没有基础接口许可(包含已过期)的userid
         */
        private String unlicenseduser;
        /**
         * 消息id，用于撤回应用消息
         */
        private String msgid;
        /**
         * 仅消息类型为“按钮交互型”，“投票选择型”和“多项选择型”的模板卡片消息返回，应用可使用response_code调用更新模版卡片消息接口，24小时内有效，且只能使用一次
         */
        @JsonProperty("response_code")
        private String responseCode;


    }
}
