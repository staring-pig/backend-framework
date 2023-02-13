package com.staringpig.framework.wechat.offiaccount;

import com.staringpig.framework.wechat.account.OPAppAccount;

import java.util.Optional;

/**
 * 微信公众号账号仓储
 */
public interface OAAccountRepository<T extends OPAppAccount> {

    /**
     * 通过open-id查询用户账户
     *
     * @param openId open-id
     * @return 账户
     */
    Optional<T> queryByOpenId(String openId);

    /**
     * 通过unionId查询账户
     *
     * @param unionId 平台账户
     * @return 账户
     */
    Optional<T> queryByUnionId(String unionId);
}
