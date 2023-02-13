package com.staringpig.framework.wechat.corp;

import com.staringpig.framework.wechat.account.OPAccount;
import lombok.Getter;

/**
 * 客户
 */
public class Customer {

    /**
     * 对应一个外部联系人在当前企业微信中的id
     */
    @Getter
    private final String externalUserId;
    /**
     * 开放平台账号
     */
    @Getter
    private final OPAccount opAccount;

    public Customer(String externalUserId, OPAccount opAccount) {
        this.externalUserId = externalUserId;
        this.opAccount = opAccount;
    }
}
