package com.staringpig.framework.wechat;

import com.staringpig.framework.wechat.offiaccount.OAAccount;
import com.staringpig.framework.wechat.oplatform.OPAccount;

public class OPOPAccount extends OAAccount {

    private OPAccount opAccount;

    public OPOPAccount(String openId, String oaAppId) {
        super(openId, oaAppId);
    }
}
