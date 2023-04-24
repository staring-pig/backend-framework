package com.staringpig.framework.ai.endpoint.openai.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountTokensCommand {
    /**
     * 模型名称
     */
    private String model;
    /**
     * 内容
     */
    private List<ChatMessage> content;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessage {
        /**
         * Must be either 'system', 'user', or 'assistant'.<br>
         * You may use {@link ChatMessageRole} enum.
         */
        String role;
        /**
         * 名称
         */
        String name;
        /**
         * 内容
         */
        String content;
    }
}
