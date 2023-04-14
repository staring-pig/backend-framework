package com.staringpig.framework.ai.capability;

import java.util.function.Consumer;

/**
 * 图片修改能力
 */
public interface ImageEditing extends Capability {

    /**
     * 修改图片
     */
    void edit(Image origin, Prompt prompt, Consumer<Image> onReply);
}
