package com.example.Heunduljang.invitation.controller;

import com.example.Heunduljang.common.base.BaseResponse;
import com.example.Heunduljang.invitation.dto.request.InvitationRequestDto;
import com.example.Heunduljang.invitation.service.InvitationService;
import com.example.Heunduljang.user.entity.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="초대장")
@RequiredArgsConstructor
@Validated
@RequestMapping("/invitation")
@RestController
public class InvitationController {

    private final InvitationService invitationService;

    /**
     * 초대장 만들기
     */
    @PostMapping
    public BaseResponse<User> save(@Validated @RequestBody InvitationRequestDto invitationRequestDto, @AuthenticationPrincipal User user ){
        invitationService.saveInvitation(user,invitationRequestDto);
        return BaseResponse.onSuccess(user);
    }
}
