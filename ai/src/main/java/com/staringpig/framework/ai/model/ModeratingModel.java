package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.Moderating;

public abstract class ModeratingModel extends AIModel implements Moderating {

    public ModeratingModel(String name) {
        super(name);
    }

}
