package com.staringpig.framework.ai.capability;

import java.util.Locale;
import java.util.function.Consumer;

/**
 * transcribes audio into the input language
 */
public interface Transcription extends Capability {

    /**
     * audio to text
     */
    void transcribe(Audio audio, Locale language, Consumer<String> onReply);

    /**
     * audio to text with prompt
     */
    void transcribe(Audio audio, Prompt prompt, Locale language, Consumer<String> onReply);
}
