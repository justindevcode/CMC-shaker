package com.example.Heunduljang.home.controller;

import com.example.Heunduljang.common.base.BaseResponse;
import com.example.Heunduljang.home.dto.request.UserLocationRequestDto;
import com.example.Heunduljang.home.dto.response.UserHomeResponseDto;
import com.example.Heunduljang.home.service.HomeService;

import com.example.Heunduljang.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="홈화면")
@RequiredArgsConstructor
@RequestMapping("/home")
@RestController
public class HomeController {

	private final HomeService homeService;

	@ApiOperation(
		value = "유저 위도경도 저장",
		notes = "유저 위도경도 저장 API"
	)
	@PostMapping("/location")
	public BaseResponse<String> putUserLocation(@RequestBody UserLocationRequestDto userLocationRequestDto) {
		return BaseResponse.onSuccess(homeService.putUserLocation(userLocationRequestDto));
	}

	@ApiOperation(
		value = "유저기준 주변 유저위치검색",
		notes = "주변 유저위치검색"
	)
	@GetMapping("/{appleId}")
	private BaseResponse<List<UserHomeResponseDto>> findUsersWithinRadius(
		@PathVariable String appleId) {


		List<User> user = homeService.test(appleId);//findUsersWithinRadius(appleId);

		List<UserHomeResponseDto> userHomeResponseDtos = new ArrayList<>();
		for (User user1 : user) {
			UserHomeResponseDto userHomeResponseDto = UserHomeResponseDto.builder()
				.appleId(user1.getAppleId())
				.userNickName(user1.getNickname())
				.imageUrl(user1.getProfileImage())
				.latitude(user1.getLatitude())
				.longitude(user1.getLongitude())
				.build();
			userHomeResponseDtos.add(userHomeResponseDto);
		}
		return BaseResponse.onSuccess(userHomeResponseDtos);
	}


}
