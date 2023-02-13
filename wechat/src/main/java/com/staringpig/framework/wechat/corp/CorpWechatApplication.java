package com.staringpig.framework.wechat.corp;

import com.staringpig.framework.wechat.OPAppType;
import com.staringpig.framework.wechat.OPApplication;
import lombok.Getter;
import net.dreamlu.mica.core.utils.StringPool;

/**
 * 企业微信应用
 *
 * @author niumy
 */
public abstract class CorpWechatApplication extends OPApplication {
    /**
     * 企业id
     */
    @Getter
    private final String corpId;

    /**
     * 通讯录管理secret
     */
    @Getter
    private final String addressBookSecret;
    /**
     * 外部联系人管理secret
     */
    @Getter
    private final String externalAddressBookSecret;

    protected CorpWechatApplication(String corpId, String addressBookSecret,
                                    String externalAddressBookSecret) {
        super(StringPool.EMPTY, StringPool.EMPTY, OPAppType.CORP_WECHAT, StringPool.EMPTY);
        this.corpId = corpId;
        this.addressBookSecret = addressBookSecret;
        this.externalAddressBookSecret = externalAddressBookSecret;
    }
}
