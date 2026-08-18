package com.spring.ai.demo.ex.springopenaidemo.service.impl;

import com.spring.ai.demo.ex.springopenaidemo.service.ChatServiceI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatServiceI {

    private final ChatClient chatClient;

    public ChatServiceImpl(@Qualifier("openAiChatClientBean") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ResponseEntity<String> content(String question) {
        return ResponseEntity
                .ok(chatClient
                        .prompt()
                        .user(question)
                        .call()
                        .content());
    }
}
