package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.Usage;
import lombok.Getter;

public abstract class AIModel<T extends Usage> implements Costing<T> {

    /**
     * 模型名称
     */
    @Getter
    private final String name;

    public AIModel(String name) {
        this.name = name;
    }
}
