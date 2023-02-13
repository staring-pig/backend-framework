package com.staringpig.framework.wechat.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息体内数据类型
 *
 * @param <T> 数据类型
 */
@Getter
@AllArgsConstructor
public abstract class MessageDataType<T> {

    /**
     * T 类型的值
     */
    T value;
}
