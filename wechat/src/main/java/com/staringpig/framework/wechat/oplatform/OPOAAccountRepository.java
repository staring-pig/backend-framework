package com.staringpig.framework.wechat.oplatform;

import com.staringpig.framework.wechat.offiaccount.account.OAAccountRepository;

import java.util.Optional;

public interface OPOAAccountRepository<T extends OPOAAccount> extends OAAccountRepository<T> {

    /**
     * 通过unionId查询用户账户
     */
    Optional<T> queryByUnionId(String unionId);
}
