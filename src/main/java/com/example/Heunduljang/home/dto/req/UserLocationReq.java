package com.example.Heunduljang.home.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(value = "유저 위치 등록 Request")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserLocationReq {

	@ApiModelProperty(value = "유저 식별 Id")
	private Long userId;

	@ApiModelProperty(value = "위도")
	private double latitude;

	@ApiModelProperty(value = "경도")
	private double longitude;

}
