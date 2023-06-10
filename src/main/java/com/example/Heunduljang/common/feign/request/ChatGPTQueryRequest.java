package com.example.Heunduljang.common.feign.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatGPTQueryRequest {

    @NotBlank
    private String model;

    private List<Message> messages;

//    @NotBlank
//    private String prompt;

//    @NotNull
//    private int temperature;
//
//    @NotNull
//    private int max_tokens;

    public static class Message{
        private String role;
        private String content;
    }
}
