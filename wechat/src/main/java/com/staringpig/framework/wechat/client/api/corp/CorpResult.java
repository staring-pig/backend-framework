package com.staringpig.framework.wechat.client.api.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staringpig.framework.wechat.client.CorpWechatServerException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 微信小程序返回结果外层包装
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public abstract class CorpResult {

    @JsonProperty("errcode")
    private Integer errCode = 0;

    @JsonProperty("errmsg")
    private String errMsg = "ok";

    public void isOK() {
        if (this.getErrCode() != null && this.getErrCode() != 0) {
//            log.warn("企业微信接口异常: code:[{}], com.staringpig.backendframework.message:[{}]", this.getErrCode(), this.getErrMsg());
            throw new CorpWechatServerException(this.getErrMsg());
        }
    }
}
