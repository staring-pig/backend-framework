package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.Usage;
import lombok.Getter;

public abstract class AIModel<T extends Usage> {

    /**
     * 模型名称
     */
    @Getter
    private final String name;

    private final Costing<T> costing;

    public AIModel(String name, Costing<T> costing) {
        this.name = name;
        this.costing = costing;
    }

    protected void cost(String user, T usage) {
        this.costing.costing(user, this, usage);
    }
}
