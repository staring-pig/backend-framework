package com.staringpig.framework.ai.model;

import lombok.Getter;

public abstract class AIModel {

    /**
     * 模型名称
     */
    @Getter
    private final String name;

    public AIModel(String name) {
        this.name = name;
    }
}
