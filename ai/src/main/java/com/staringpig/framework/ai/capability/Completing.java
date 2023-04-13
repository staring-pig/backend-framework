package com.staringpig.framework.ai.capability;

/**
 * 文本完成能力，一般是通过一个prompt来完成文本
 */
public interface Completing extends Capability {

    /**
     * 补全
     */
    Completion complete(Prompt prompt);

    /**
     * 完成后的内容
     */
    public abstract class Completion {

    }
}
