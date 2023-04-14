package com.staringpig.framework.ai.capability;

import lombok.Getter;

/**
 * 一个提示语
 */
public abstract class Prompt {

    /**
     * 提示语的内容
     */
    @Getter
    private final String content;

    public Prompt(String content) {
        this.content = content;
    }
}
