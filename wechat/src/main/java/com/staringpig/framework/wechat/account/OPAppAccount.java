package com.staringpig.framework.wechat.account;

import com.staringpig.framework.wechat.OPApplication;
import lombok.Getter;

/**
 * 微信公众平台应用账户
 * <p>
 * OpenPlatformApplicationAccount
 *
 * @author niumy
 */
@Getter
public abstract class OPAppAccount {
    /**
     * 对应用户id
     */
    private final String openId;
    /**
     * 对应的应用
     */
    private final OPApplication application;
    /**
     * 开放平台id
     */
    private final OPAccount opAccount;

    /**
     * 唯一id
     */
    public abstract Long getId();

    public OPAppAccount(String openId, OPApplication application, OPAccount opAccount) {
        this.openId = openId;
        this.application = application;
        this.opAccount = opAccount;
    }
}
