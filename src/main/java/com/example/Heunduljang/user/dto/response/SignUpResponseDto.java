package com.example.Heunduljang.user.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
//@ToString
//@EqualsAndHashCode
public class SignUpResponseDto {

    private final String appleId;

    public SignUpResponseDto(String appleId) {
        this.appleId = appleId;
    }

//    public static SignUpResponseDto of()
}
