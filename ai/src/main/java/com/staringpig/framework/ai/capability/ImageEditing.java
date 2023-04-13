package com.staringpig.framework.ai.capability;

/**
 * 图片修改能力
 */
public interface ImageEditing extends Capability {

    /**
     * 修改图片
     */
    Image edit(Image origin, Prompt prompt);
}
