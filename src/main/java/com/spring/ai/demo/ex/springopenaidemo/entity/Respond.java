package com.spring.ai.demo.ex.springopenaidemo.entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Respond {

    private String title;
    private String content;
    private String year;

}
