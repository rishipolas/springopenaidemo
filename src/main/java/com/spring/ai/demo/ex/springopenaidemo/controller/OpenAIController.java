package com.spring.ai.demo.ex.springopenaidemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

    private final ChatClient chatClient;

    public OpenAIController(@Qualifier("openAiChatClientBean") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam(value = "q", required = true) String question) {
       return ResponseEntity
               .ok(chatClient
                       .prompt()
                       .user(question)
                       .call()
                       .content());
    }
}
