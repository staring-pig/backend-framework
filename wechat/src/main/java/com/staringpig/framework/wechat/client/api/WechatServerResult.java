package com.staringpig.framework.wechat.client.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.WechatServerException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 微信的服务端返回结果外层包装
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class WechatServerResult {

    @JsonProperty("errcode")
    private Integer errCode = 0;

    @JsonProperty("errmsg")
    private String errMsg = "ok";

    public void isOK() {
        if (this.errCode != null && this.errCode != 0) {
            throw new WechatServerException(this.errMsg);
        }
    }
}
