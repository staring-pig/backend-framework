package com.staringpig.framework.ai.usage;

import com.staringpig.framework.ai.model.AIModel;

/**
 * 计算费用
 */
public interface Costing<U extends Usage> {

    /**
     * 计费
     */
    void costing(String user, AIModel<U> model, U usage);
}
