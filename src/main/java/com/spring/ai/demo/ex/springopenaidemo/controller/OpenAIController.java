package com.spring.ai.demo.ex.springopenaidemo.controller;

import com.spring.ai.demo.ex.springopenaidemo.entity.Respond;
import com.spring.ai.demo.ex.springopenaidemo.service.impl.ChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OpenAIController {

    private final ChatServiceImpl chatServiceImpl;

    public OpenAIController(ChatServiceImpl chatServiceImpl) {
        this.chatServiceImpl = chatServiceImpl;
    }

    @GetMapping("/ask")
    public  ResponseEntity<List<Respond>> ask(@RequestParam(value = "q", required = true) String question) {
       return chatServiceImpl.content(question);
    }

}
