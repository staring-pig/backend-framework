package com.staringpig.framework.ai.endpoint.openai;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenAIProperties {
    /**
     * auth token
     */
    private String token;
}
