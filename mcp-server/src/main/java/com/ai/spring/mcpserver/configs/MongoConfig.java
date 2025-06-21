package com.ai.spring.mcpserver.configs;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(@Value("${mongodb.uri}") String mongoUri) {
        if (mongoUri == null || mongoUri.isEmpty()) {
            throw new IllegalArgumentException("MongoDB URI must not be null or empty");
        }
        return com.mongodb.client.MongoClients.create(mongoUri);
    }
}
