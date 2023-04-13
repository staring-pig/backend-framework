package com.staringpig.framework.ai.capability;

/**
 * 内容分类能力
 */
public interface Moderating extends Capability {

    /**
     * 对输入分类
     */
    Moderation moderate(String input);


    public abstract class Moderation {

        /**
         * Set to true if the model classifies the content as violating content policy, false otherwise
         */
        public boolean flagged;
    }
}
