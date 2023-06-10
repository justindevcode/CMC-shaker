package com.example.Heunduljang.common.exception;


import com.example.Heunduljang.common.error.ErrorCode;
import lombok.Getter;

import java.util.Map;

@Getter
public class UnauthorizedException extends BaseException {

    private String message;

    public UnauthorizedException(ErrorCode errorCode, Map<String, String> data) {
        super(errorCode, data);
    }

    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }

}
