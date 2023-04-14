package com.staringpig.framework.ai.capability;

import java.util.function.Consumer;

/**
 * 文本修改能力
 */
public interface TextEditing extends Capability {

    /**
     * 修改输入文本，根据指令修改，输出文本
     */
    void edit(String input, Instruction instruction, Consumer<String> onReply);
}
