package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.Moderating;
import com.staringpig.framework.ai.usage.Costing;
import com.staringpig.framework.ai.usage.Usage;

public abstract class ModeratingModel<T extends Usage> extends AIModel<T> implements Moderating {

    public ModeratingModel(String name, Costing<T> costing) {
        super(name, costing);
    }

}
