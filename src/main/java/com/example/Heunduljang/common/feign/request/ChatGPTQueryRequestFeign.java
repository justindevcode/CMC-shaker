package com.example.Heunduljang.common.feign.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatGPTQueryRequestFeign {

    @NotBlank
    private String model;

    @NotBlank
    private List<Message> messages;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Message{

        @NotBlank
        private String role;

        @NotBlank
        private String content;
    }

}
