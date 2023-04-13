package com.staringpig.framework.ai.capability;

/**
 * Creates a variation of a given image
 */
public interface ImageVariation extends Capability {

    /**
     * variate a given image
     */
    Image variate(Image origin);
}
