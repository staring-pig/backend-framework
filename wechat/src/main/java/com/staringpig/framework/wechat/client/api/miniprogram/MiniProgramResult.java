package com.staringpig.framework.wechat.client.api.miniprogram;

import com.staringpig.framework.wechat.client.WechatMiniProgramServerException;
import com.staringpig.framework.wechat.client.api.WechatServerResult;
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
public abstract class MiniProgramResult extends WechatServerResult {

    public void isOK() {
        if (this.getErrCode() != null && this.getErrCode() != 0) {
            throw new WechatMiniProgramServerException(this.getErrMsg());
        }
    }
}
