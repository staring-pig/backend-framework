package com.staringpig.framework.ai.model;

import com.staringpig.framework.ai.capability.Transcription;

public abstract class TranscriptionModel extends AIModel implements Transcription {

    public TranscriptionModel(String name) {
        super(name);
    }
}
