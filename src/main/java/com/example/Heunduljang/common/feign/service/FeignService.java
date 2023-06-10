package com.example.Heunduljang.common.feign.service;

import com.example.Heunduljang.common.feign.ChatGptClient;
import com.example.Heunduljang.common.feign.request.ChatGPTQueryRequestFeign;
import com.example.Heunduljang.common.feign.response.ChatGPTResponse;
import com.example.Heunduljang.invitation.entity.Invitation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeignService {

    private final ChatGptClient client;

    public ChatGPTResponse getQueryAnswer(Invitation invitation){

        String prompt = makePrompt(invitation.getConcept(), invitation.getReason());

        List<ChatGPTQueryRequestFeign.Message> messages = new ArrayList<>();
        messages.add(new ChatGPTQueryRequestFeign.Message("user", prompt));
        ChatGPTQueryRequestFeign chatGPTQueryRequestFeign = ChatGPTQueryRequestFeign.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .build();
        System.out.println("prompt = " + prompt);

        ChatGPTResponse gptQueryApiResponse = client.getGPTQueryApiResponse(chatGPTQueryRequestFeign);
        System.out.println("gptQueryApiResponse.getChoices().get(0).getMessages().get(0).getContent() = " + gptQueryApiResponse.getChoices().get(0).getMessage().getContent());
        return gptQueryApiResponse;
    }

    private String makePrompt(String concept, String reason) {
        return String.format("나는 %s. %s. 함께 할 사람을 찾는데 내가 할 초대 멘트를 %s처럼 30자 이내로 알려줘.",
                concept, reason, concept);
    }
}
