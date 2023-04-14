package com.staringpig.framework.ai.capability;

import java.util.function.Consumer;

public interface VoiceGenerate extends Capability {

    void generate(Prompt prompt, Consumer<Voice> onReply);
}
