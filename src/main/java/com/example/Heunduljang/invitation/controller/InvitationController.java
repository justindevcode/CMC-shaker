package com.example.Heunduljang.invitation.controller;

import com.example.Heunduljang.common.base.BaseResponse;
import com.example.Heunduljang.common.feign.response.ChatGPTResponse;
import com.example.Heunduljang.common.feign.service.FeignService;
import com.example.Heunduljang.invitation.dto.request.InvitationRequestDto;
import com.example.Heunduljang.invitation.entity.Invitation;
import com.example.Heunduljang.invitation.service.InvitationService;
import com.example.Heunduljang.user.entity.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Api(tags="초대장")
@RequiredArgsConstructor
@Validated
@RequestMapping("/invitation")
@RestController
public class InvitationController {

    private final InvitationService invitationService;
    private final FeignService feignService;

    /**
     * 초대장 만들기
     */

    @PostMapping
    public BaseResponse<Invitation> save(@Validated @RequestBody InvitationRequestDto invitationRequestDto, @AuthenticationPrincipal User user ){
        Invitation invitation = invitationService.saveInvitation(user, invitationRequestDto);
        return BaseResponse.onSuccess(invitation);
    }

    /**
     * 초대장 멘트 만들기
     */
    @GetMapping("/{invitationId}")
    public BaseResponse<Invitation> saveComment(@PathVariable(value = "invitationId",required = false)
                                                    @NotNull(message = "초대장 ID를 입력해주세요.")
                                      Long invitationId){
        System.out.println("invitationId = " + invitationId);
        Invitation invitation = invitationService.findInvitation(invitationId);
        ChatGPTResponse queryAnswer = feignService.getQueryAnswer(invitation);
        System.out.println("queryAnswer.getChoices().get(0).getMessages().get(0).getContent() = " + queryAnswer.getChoices().get(0).getMessage().getContent());
        Invitation savedInvitation= invitationService.saveComment(invitation,queryAnswer);
        return BaseResponse.onSuccess(savedInvitation);
    }

    @GetMapping("/send/{invitationId}")
    public BaseResponse<String> send(@PathVariable(value = "invitationId",required = false)
                                             @NotNull(message = "초대장 ID를 입력해주세요.")
                                             Long invitationId){
        invitationService.sendInvitation(invitationId);
        return BaseResponse.onSuccess("성공하였습니다.");
    }


}
