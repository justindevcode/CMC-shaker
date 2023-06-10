package com.example.Heunduljang.common.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public final class FeignInterceptor implements RequestInterceptor {

    @Value("${chatgpt.secret}")
    private String key;

    @Override
    public void apply(RequestTemplate template) {
        if (template.method() == HttpMethod.POST.name()) {
            template.header("Content-Type", "application/json");
            template.header("Authorization", "Bearer " + key);
            log.info("[Authorization] {}", template.headers().get("Authorization"));
        }
    }
}
