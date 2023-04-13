package com.staringpig.framework.ai.capability;

import java.util.Locale;

/**
 * transcribes audio into the input language
 */
public interface Transcription extends Capability {

    /**
     * audio to text
     */
    String transcribe(Audio audio, Locale language);

    /**
     * audio to text with prompt
     */
    String transcribe(Audio audio, Prompt prompt, Locale language);
}
