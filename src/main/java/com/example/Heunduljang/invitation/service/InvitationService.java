package com.example.Heunduljang.invitation.service;

import com.example.Heunduljang.common.exception.NotFoundException;
import com.example.Heunduljang.common.feign.response.ChatGPTResponse;
import com.example.Heunduljang.invitation.dto.request.InvitationRequestDto;
import com.example.Heunduljang.invitation.entity.Invitation;
import com.example.Heunduljang.invitation.repository.InvitationRepository;
import com.example.Heunduljang.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    public User saveInvitation(User user, InvitationRequestDto invitationRequestDto){

        Invitation invitation = Invitation.builder()
                .receiverUser(user)
                .personnel(invitationRequestDto.getPersonnel())
                .reason(invitationRequestDto.getReason())
                .concept(invitationRequestDto.getConcept())
                .kakaoLink(invitationRequestDto.getKakaoLink())
                .build();

        invitationRepository.save(invitation);
        return user;
    }

    public Invitation findInvitation(Long invitationId){
        return invitationRepository.findById(invitationId).orElseThrow(()-> new NotFoundException("초대장을 찾을 수 없습니다."));
    }


    public Invitation saveComment(Invitation invitation, ChatGPTResponse queryAnswer) {
        invitation.setComment(queryAnswer.getChoices().get(0).getMessage().getContent());
        return invitationRepository.save(invitation);
    }
}
