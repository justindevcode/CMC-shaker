package com.example.Heunduljang.invitation.service;

import com.example.Heunduljang.common.exception.NotFoundException;
import com.example.Heunduljang.common.feign.response.ChatGPTResponse;
import com.example.Heunduljang.home.service.HomeService;
import com.example.Heunduljang.invitation.dto.request.InvitationRequestDto;
import com.example.Heunduljang.invitation.entity.Invitation;
import com.example.Heunduljang.invitation.repository.InvitationRepository;
import com.example.Heunduljang.user.entity.User;
import com.example.Heunduljang.user.service.UserService;
import com.example.Heunduljang.user_invitation.entity.UserInvitation;
import com.example.Heunduljang.user_invitation.repository.UserInvitationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final HomeService homeService;
    private final UserInvitationRepository userInvitationRepository;

    public Invitation saveInvitation(User user, InvitationRequestDto invitationRequestDto){

        Invitation invitation = Invitation.builder()
                .receiverUser(user)
                .personnel(invitationRequestDto.getPersonnel())
                .reason(invitationRequestDto.getReason())
                .concept(invitationRequestDto.getConcept())
                .kakaoLink(invitationRequestDto.getKakaoLink())
                .build();

        return invitationRepository.save(invitation);
    }

    public Invitation findInvitation(Long invitationId){
        return invitationRepository.findById(invitationId).orElseThrow(()-> new NotFoundException("초대장을 찾을 수 없습니다."));
    }


    public Invitation saveComment(Invitation invitation, ChatGPTResponse queryAnswer) {
        invitation.setComment(queryAnswer.getChoices().get(0).getMessage().getContent());
        return invitationRepository.save(invitation);
    }

    public void sendInvitation(Long invitationId){
        Invitation invitation = invitationRepository.findById(invitationId).orElseThrow(() -> new NotFoundException("초대장을 찾을 수 없습니다."));
        List<User> usersWithinRadius = homeService.findUsersWithinRadius(
                invitation.getReceiverUser().getAppleId());
        for (User users : usersWithinRadius) {
            UserInvitation userInvitation = UserInvitation.builder()
                    .invitation(invitation)
                    .creatorUser(invitation.getReceiverUser())
                    .receiverUser(users)
                    .status(false)
                    .build();
            userInvitationRepository.save(userInvitation);
        }
    }




}
