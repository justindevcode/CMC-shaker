package com.example.Heunduljang.common.feign.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor // 역직렬화를 하기 위해서는 기본생성자가 필요함
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class ChatGPTResponse {

    @JsonProperty("choices")
    private List<Choice> choices;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ToString
    public static class Choice{
        @JsonProperty("message")
        private Message message;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ToString
    public static class Message{
        @JsonProperty("content")
        private String content;
    }
}
