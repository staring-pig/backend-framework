package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.Capability;

import java.util.Set;

public interface AIModel {

    /**
     * AI 模型的能力s
     */
    Set<Capability> capabilities();
}
