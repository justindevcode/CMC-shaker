package com.example.Heunduljang.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel(value = "회원가입 request DTO")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class SignUpRequestDto {


    @ApiModelProperty(value = "애플 아이디")
    @NotBlank(message = "애플 아이디는 값이 있어야 합니다.")
    private String appleId;

    @ApiModelProperty(value = "닉네임")
    @Pattern(regexp = "^[0-9a-zA-Z가-힣]{1,8}", message = "닉네임은 8자이하 한글,숫자,영어로만 이루어져야 합니다.")
    private String nickname;

    @ApiModelProperty(value = "성별")
    @NotBlank(message = "깃허브 닉네임은 값이 있어야 합니다.")
    private String gender;

    @ApiModelProperty(value = "나이")
    @NotBlank(message = "이메일은 값이 있어야 합니다.")
    private String age;

    @ApiModelProperty(value = "디바이스 아이디")
    @NotBlank(message = "디바이스 아이디 값이 있어야 합니다.")
    private String deviceId;




}
