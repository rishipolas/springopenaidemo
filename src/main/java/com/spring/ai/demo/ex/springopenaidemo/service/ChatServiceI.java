package com.spring.ai.demo.ex.springopenaidemo.service;

import com.spring.ai.demo.ex.springopenaidemo.entity.Respond;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatServiceI {

    ResponseEntity<List<Respond>> content(String question);
}
