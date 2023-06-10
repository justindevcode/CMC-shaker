package com.example.Heunduljang.home.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 위치 등록 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserLocationRequestDto {

	@ApiModelProperty(value = "유저 식별 애플아이디")
	private String appleId;

	@ApiModelProperty(value = "위도")
	private double latitude;

	@ApiModelProperty(value = "경도")
	private double longitude;

}
