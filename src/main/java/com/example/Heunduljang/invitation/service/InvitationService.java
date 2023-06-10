package com.example.Heunduljang.invitation.service;

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
                .concept(invitationRequestDto.getConcept())
                .kakaoLink(invitationRequestDto.getKakaoLink())
                .build();

        invitationRepository.save(invitation);
        return user;
    }
}
