package com.spring.ai.demo.ex.springopenaidemo.service.impl;

import com.spring.ai.demo.ex.springopenaidemo.entity.Respond;
import com.spring.ai.demo.ex.springopenaidemo.service.ChatServiceI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatServiceI {

    private final ChatClient chatClient;

    public ChatServiceImpl(@Qualifier("openAiChatClientBean") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ResponseEntity<List<Respond>> content(String question) {
        return ResponseEntity
                .ok(this.getChatClient(question));
    }

    private  List<Respond> getChatClient(String question) {
           return chatClient.prompt(question)
                    .call()
                    .entity(new ParameterizedTypeReference<List<Respond>>() {});

//        return chatClient
//                .prompt()
//                .user(question)
//                .system("As a Expert in Specific field")
//                .call()
//                .content();
    }
}
