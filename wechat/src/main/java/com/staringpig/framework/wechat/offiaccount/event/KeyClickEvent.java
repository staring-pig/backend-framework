package com.staringpig.framework.wechat.offiaccount.event;

import com.staringpig.framework.wechat.account.OPAppAccount;
import lombok.Getter;

/**
 * 自定义菜单key
 */
public final class KeyClickEvent extends OAEventMessage {

    /**
     * 按钮key的枚举值
     */
    @Getter
    private final Key key;

    public KeyClickEvent(OPAppAccount opAppAccount, Long createTime, Key key) {
        super(opAppAccount, createTime, Type.CLICK);
        this.key = key;
    }

    public interface Key {
    }

    public interface KeyConverter<T extends Key> {

        T convert(String keyString);
    }
}
