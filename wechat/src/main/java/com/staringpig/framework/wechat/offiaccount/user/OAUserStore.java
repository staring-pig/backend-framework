package com.staringpig.framework.wechat.offiaccount.user;

import java.util.Optional;

/**
 * 微信公众号账号仓储
 */
public interface OAUserStore {

    /**
     * 通过open-id查询用户账户
     *
     * @param openId open-id
     * @return 账户
     */
    Optional<OAUser> queryByOpenId(String openId);

    OAUser saveIt(OAUser OAUser);
}
