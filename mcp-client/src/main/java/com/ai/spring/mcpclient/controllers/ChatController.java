package com.ai.spring.mcpclient.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatClient chatClient;

    @PostMapping("/ask")
    public String chat(@RequestBody String userInput) {
        try {
            return chatClient.prompt(userInput).call().content();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}