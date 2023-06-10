package com.example.Heunduljang.common.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());
        if(httpStatus== (HttpStatus.UNAUTHORIZED)){
            log.info("[FeignErrorDecoder] Http Status {}",httpStatus);
            throw new IllegalArgumentException("ERROR : API 토큰키 누락");
        }

        return errorDecoder.decode(methodKey,response);
    }
}
