package com.staringpig.framework.wechat.account;

import lombok.Getter;

/**
 * 微信开放平台账户(Open-Platform-Account)
 * <p>
 * 相当于用户在微信开放平台的唯一性
 * <p>
 * 唯一确定一个用户是通过 这个账户的id来的，对应微信开放平台unionId
 * <p>
 * 如果开发者拥有多个移动应用、网站应用、和公众帐号（包括小程序），可通过 UnionID 来区分用户的唯一性，因为只要是同一个微信开放平台帐号下的移动应用、网站应用和公众帐号（包括小程序），用户的 UnionID 是唯一的。换句话说，同一用户，对同一个微信开放平台下的不同应用，UnionID是相同的。
 *
 * @author niumy
 */
public class OPAccount {

    @Getter
    private final String unionId;

    public OPAccount(String unionId) {
        this.unionId = unionId;
    }
}
