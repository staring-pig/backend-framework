package com.staringpig.framework.ai.capability;

/**
 * 图片生成能力
 */
public interface ImageGenerating extends Capability {

    /**
     * 生成图片
     */
    Image generate(Prompt prompt);

}
