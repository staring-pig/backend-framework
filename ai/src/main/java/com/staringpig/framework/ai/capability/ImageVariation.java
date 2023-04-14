package com.staringpig.framework.ai.capability;

import java.util.function.Consumer;

/**
 * Creates a variation of a given image
 */
public interface ImageVariation extends Capability {

    /**
     * variate a given image
     */
    void variate(Image origin, Consumer<Image> onReply);
}
