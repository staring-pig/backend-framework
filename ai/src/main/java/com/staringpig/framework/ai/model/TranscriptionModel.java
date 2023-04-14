package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.Transcription;
import com.staringpig.framework.ai.usage.Usage;

public abstract class TranscriptionModel<T extends Usage> extends AIModel<T> implements Transcription {

    public TranscriptionModel(String name) {
        super(name);
    }
}
