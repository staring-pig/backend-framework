package com.staringpig.framework.wechat.oplatform;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;

public final class OPOPAccount extends OAAccount {

    private OPAccount opAccount;

    public OPOPAccount(String openId, String oaAppId) {
        super(openId, oaAppId);
    }

    public String getUnionId() {
        return this.opAccount.getUnionId();
    }
}
