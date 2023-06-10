package com.example.Heunduljang.letter_storage.controller;

import com.example.Heunduljang.common.base.BaseResponse;
import com.example.Heunduljang.letter_storage.dto.response.InvitationListResponseDto;
import com.example.Heunduljang.letter_storage.dto.response.InvitationResponseDto;
import com.example.Heunduljang.letter_storage.service.LetterStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="초대장 보관함")
@RequiredArgsConstructor
@Validated
@RequestMapping("/invitation-storage")
@RestController
public class LetterStorageController {

	private final LetterStorageService letterStorageService;

	@ApiOperation(
		value = "보낸초대함보기",
		notes = "보낸 초대함을 보는 API"
	)
	@ApiImplicitParam(
		name = "appleId"
		, value = "유저식별 애플아이디"
		, required = true
	)
	@GetMapping("/send/{appleId}")
	public BaseResponse<List<InvitationListResponseDto>> getSendInvitation(
		@PathVariable String appleId) {
		List<InvitationListResponseDto> sendInvitationByUser = letterStorageService.getSendInvitationByUser(
			appleId);
		if (sendInvitationByUser == null) {
			return BaseResponse.onFailure(400, "appleId 값으로 유저가 존재하지않습니다 appleId = " + appleId,
				null);
		}
		return BaseResponse.onSuccess(sendInvitationByUser);

	}

	@ApiOperation(
		value = "받은 초대함",
		notes = "받은 초대함을 보는 API"
	)
	@ApiImplicitParam(
		name = "appleId"
		, value = "유저식별 애플아이디"
		, required = true
	)
	@GetMapping("/receive/{appleId}")
	public BaseResponse<List<InvitationListResponseDto>> getReceiveInvitationByUser(
		@PathVariable String appleId) {

		List<InvitationListResponseDto> receiveInvitationByUser = letterStorageService.getReceiveInvitationByUser(
			appleId);

		if (receiveInvitationByUser == null) {
			return BaseResponse.onFailure(400, "appleId 값으로 유저가 존재하지않습니다 appleId = " + appleId,
				null);
		}
		return BaseResponse.onSuccess(receiveInvitationByUser);

	}

	@ApiOperation(
		value = "초대장 디테일 확인",
		notes = "초대장 디테일을 보는 API"
	)
	@ApiImplicitParam(
		name = "invitationId"
		, value = "초대장식별자"
		, required = true
	)
	@GetMapping("/{invitationId}")
	public BaseResponse<InvitationResponseDto> getInvitation(@PathVariable Long invitationId) {
		return BaseResponse.onSuccess(
			letterStorageService.getInvitationByInvitationId(invitationId));
	}

	@ApiOperation(
		value = "초대 끝내기",
		notes = "초대 끝내는 API"
	)
	@ApiImplicitParam(
		name = "invitationId"
		, value = "초대장식별자"
		, required = true
	)
	@PatchMapping("/finish/{invitationId}")
	public BaseResponse<String> patchInvitationFinish(@PathVariable Long invitationId) {
		return BaseResponse.onSuccess(
			letterStorageService.patchInvitationFinish(invitationId));
	}

	@ApiOperation(
		value = "초대 수락하기",
		notes = "초대 수락하는 API"
	)
	@ApiImplicitParams(
		{
			@ApiImplicitParam(
				name = "invitationId"
				, value = "초대장식별자"
				, required = true
			),
			@ApiImplicitParam(
				name = "appleId"
				, value = "초대장에 참여하려는 유저 appleId"
				, required = true
			)
		}
	)
	@PatchMapping("/enter/{invitationId}/{appleId}")
	public BaseResponse<String> patchInvitationEnter(@PathVariable Long invitationId,
		@PathVariable String appleId) {
		return BaseResponse.onSuccess(letterStorageService.patchInvitationEnter(invitationId,appleId));
	}
}