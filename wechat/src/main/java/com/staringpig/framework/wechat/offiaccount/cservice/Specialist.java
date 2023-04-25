package com.staringpig.framework.wechat.offiaccount.cservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客服专员
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Specialist {

    /**
     * 客服编号
     */
    private String id;
    /**
     * 完整客服帐号，格式为：帐号前缀@公众号微信号
     */
    private String account;
    /**
     * 客服昵称
     */
    private String nick;
    /**
     * 客服头像
     */
    private String headImgUrl;
    /**
     * 如果客服帐号已绑定了客服人员微信号， 则此处显示微信号
     */
    private String wx;
}
