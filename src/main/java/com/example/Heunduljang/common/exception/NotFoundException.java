package com.example.Heunduljang.common.exception;

import com.example.Heunduljang.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException{

    private String message;

    public NotFoundException(String message){
        super(ErrorCode._BAD_REQUEST,message);
        this.message = message;
    }


    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}

