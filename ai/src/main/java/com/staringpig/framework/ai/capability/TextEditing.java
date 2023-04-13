package com.staringpig.framework.ai.capability;

/**
 * 文本修改能力
 */
public interface TextEditing extends Capability {

    /**
     * 修改输入文本，根据指令修改，输出文本
     */
    String edit(String input, Instruction instruction);
}
