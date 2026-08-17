package com.spring.ai.demo.ex.springopenaidemo.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

    private final ChatClient chatClient;

    public OpenAIController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/ask")
    public ResponseEntity<String> ask(@RequestParam(value = "q", required = true) String question) {
       return ResponseEntity.ok(chatClient.prompt(question)
               .call()
               .content());
    }
}
