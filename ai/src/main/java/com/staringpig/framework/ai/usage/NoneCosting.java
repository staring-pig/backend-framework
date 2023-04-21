package com.staringpig.framework.ai.usage;

import com.staringpig.framework.ai.model.AIModel;

public class NoneCosting implements Costing<NoneUsage> {

    @Override
    public void costing(String user, AIModel<NoneUsage> model, NoneUsage usage) {

    }
}
