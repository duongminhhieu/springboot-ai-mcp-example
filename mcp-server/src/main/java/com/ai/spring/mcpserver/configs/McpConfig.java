package com.ai.spring.mcpserver.configs;

import com.ai.spring.mcpserver.services.MongoService;
import com.ai.spring.mcpserver.services.PostgresService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

    /**
     * Provides a `ToolCallbackProvider` that registers tools from `MongoService` and `PostgresService`.
     * This method uses the `MethodToolCallbackProvider` to build a `ToolCallbackProvider` by
     * registering the tool objects from the provided services. The auto-configuration ensures
     * that the tool callbacks are automatically registered as MCP tools.
     *
     * @param mongoService    The MongoDB service containing tool methods.
     * @param postgresService The PostgreSQL service containing tool methods.
     * @return A `ToolCallbackProvider` that can be used to access the registered tools.
     */
    @Bean
    public ToolCallbackProvider toolCallbackProvider(MongoService mongoService, PostgresService postgresService) {
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(mongoService, postgresService)
                .build();
    }

}
