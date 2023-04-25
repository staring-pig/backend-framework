package com.staringpig.framework.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public abstract class ApiResponse {

    @JsonProperty("errcode")
    private Integer errCode = 0;

    @JsonProperty("errmsg")
    private String errMsg = "ok";

    public void isOK() {
        if (this.errCode != null && this.errCode != 0) {
            throw new ServerException(this.errMsg);
        }
    }
}
