package com.staringpig.framework.wechat.oplatform;

import com.staringpig.framework.wechat.offiaccount.account.OAAccount;
import lombok.Getter;

public class OPOAAccount extends OAAccount {

    @Getter
    private final String unionId;

    public OPOAAccount(String openId, String oaAppId, String unionId) {
        super(openId, oaAppId);
        this.unionId = unionId;
    }

}
