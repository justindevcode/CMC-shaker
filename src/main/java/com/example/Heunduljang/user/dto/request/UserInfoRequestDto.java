package com.example.Heunduljang.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저정보 수정DTO")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoRequestDto {


	@ApiModelProperty(value = "닉네임")
	@Pattern(regexp = "^[0-9a-zA-Z가-힣]{1,8}", message = "닉네임은 8자이하 한글,숫자,영어로만 이루어져야 합니다.")
	private String nickname;

	@ApiModelProperty(value = "이미지주소")
	private String imageUrl;

	@ApiModelProperty(value = "성별")
	private String gender;

	@ApiModelProperty(value = "나이")
	private String age;
}
