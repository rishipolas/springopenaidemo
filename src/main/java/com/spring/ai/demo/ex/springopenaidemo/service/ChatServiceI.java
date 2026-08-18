package com.spring.ai.demo.ex.springopenaidemo.service;

import org.springframework.http.ResponseEntity;

public interface ChatServiceI {

    ResponseEntity<String> content(String question);
}
