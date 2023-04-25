package com.staringpig.framework.wechat.offiaccount.account;

import com.staringpig.framework.wechat.offiaccount.OffiAccount;

import java.util.Optional;

/**
 * 微信公众号账号仓储
 */
public interface OAAccountRepository<T extends OAAccount> {

    /**
     * 通过open-id查询用户账户
     *
     * @param openId open-id
     * @return 账户
     */
    Optional<T> queryByOpenId(String openId);

    T saveByUserInfo(String appId, OffiAccount.UserInfo userInfo);
}
