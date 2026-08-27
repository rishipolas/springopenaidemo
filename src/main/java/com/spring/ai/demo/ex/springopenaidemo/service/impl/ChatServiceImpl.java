package com.spring.ai.demo.ex.springopenaidemo.service.impl;

import com.spring.ai.demo.ex.springopenaidemo.entity.Respond;
import com.spring.ai.demo.ex.springopenaidemo.service.ChatServiceI;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatServiceI {

    @Value("classpath:/prompt/system-message.st")
    private Resource resource;

    private final ChatClient chatClient;

    public ChatServiceImpl(@Qualifier("openAiChatClientBean") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public ResponseEntity<List<Respond>> content(String question) {
        return ResponseEntity
                .ok(this.getChatClient(question));
    }

    @Override
    public ResponseEntity<String> usePromptTemplate(String subject, String topic) {
        //Prompt template use
        var template = PromptTemplate.builder().template("What is {subject} and give me the example of this {topic}").build();

        //use render to complete the prompt template
        var render = template.render(Map.of(
                "subject", subject,
                "topic", topic
        ));

        //final
        var prompt = new Prompt(render);

        // WAY 2 alternative use
//        chatClient.prompt()
//                .system(system -> system.text("you are a professional candidate: {subject}").param("subject", subject))
//                .user(user ->  user.text("give me the example of this very brief : {topic}").param("topic", topic))
//                .call().content();


        //WAY 1
//        return ResponseEntity
//                .ok(chatClient.prompt(prompt).call().content());

        return ResponseEntity
                .ok(chatClient.prompt()
                        .system(system -> system.text("you are a professional candidate: {subject}").param("subject", subject))
                        .user(user -> user.text(this.resource).param("topic",topic))
                        .call()
                        .content()
                );
    }

    private  List<Respond> getChatClient(String question) {

//        Prompt prompt = new Prompt(question, OpenAiChatOptions
//                .builder()
//                .model("gpt-5.6-luna")
//                .temperature(0.2)
//                .maxTokens(100)
//                .maxRetries(2)
//                .build());

//        Prompt prompt = new Prompt(question);
        String s = "You are a code expert :{query}";
        return chatClient.prompt(question)
                    .user(u ->  u.text(s).param("query", " Professional to look as Architect."))
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
