package com.example.Heunduljang.common.exception;

import com.example.Heunduljang.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {

    private String message;

    public BadRequestException(String message) {
        super(ErrorCode._BAD_REQUEST);
        this.message = message;
    }

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }

}
