package com.example.Heunduljang.common.feign;

import com.example.Heunduljang.common.feign.request.ChatGPTQueryRequestFeign;
import com.example.Heunduljang.common.feign.response.ChatGPTResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name ="chatGptClient",
        url ="https://api.openai.com/v1",
        configuration =FeignConfig.class
)
public interface ChatGptClient {

    @PostMapping("/chat/completions")
    ChatGPTResponse getGPTQueryApiResponse(@RequestBody ChatGPTQueryRequestFeign chatGPTQueryRequestFeign);

}
