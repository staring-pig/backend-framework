package com.staringpig.framework.ai.usage;

import java.math.BigDecimal;

/**
 * 计算费用
 */
public interface Costing<T extends Usage> {

    /**
     * 给使用定价
     */
    BigDecimal costing(T usage);
}
