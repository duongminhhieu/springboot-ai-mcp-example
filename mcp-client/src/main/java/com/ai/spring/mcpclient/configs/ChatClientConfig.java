package com.ai.spring.mcpclient.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    /**
     * Creates and configures a `ChatClient` bean.
     *
     * @param chatClientBuilder the builder used to construct the `ChatClient`
     * @param tools             the `ToolCallbackProvider` for default tool callbacks
     * @return a fully built and configured `ChatClient` instance
     */
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        return chatClientBuilder.defaultToolCallbacks(tools).build();
    }
}