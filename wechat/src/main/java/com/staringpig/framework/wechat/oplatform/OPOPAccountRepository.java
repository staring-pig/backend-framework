package com.staringpig.framework.wechat.oplatform;

import com.staringpig.framework.wechat.offiaccount.account.OAAccountRepository;

import java.util.Optional;

public interface OPOPAccountRepository extends OAAccountRepository<OPOPAccount> {

    /**
     * 通过unionId查询用户账户
     */
    Optional<OPOPAccount> queryByUnionId(String unionId);
}
