package com.staringpig.framework.ai.capability;

import java.util.function.Consumer;

/**
 * 内容分类能力
 */
public interface Moderating extends Capability {

    /**
     * 对输入分类
     */
    void moderate(String input, Consumer<Moderation> onReply);

    abstract class Moderation {

        /**
         * Set to true if the model classifies the content as violating content policy, false otherwise
         */
        public boolean flagged;
    }
}
