package com.example.Heunduljang.user.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    @NotBlank(message = "애플 아이디 값이 존재하지 않습니다.")
    private String appleId;
}
