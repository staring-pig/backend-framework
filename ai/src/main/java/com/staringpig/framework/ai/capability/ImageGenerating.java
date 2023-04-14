package com.staringpig.framework.ai.capability;

import java.util.function.Consumer;

/**
 * 图片生成能力
 */
public interface ImageGenerating extends Capability {

    /**
     * 生成图片
     */
    void generate(Prompt prompt, Consumer<Image> onReply);
}
