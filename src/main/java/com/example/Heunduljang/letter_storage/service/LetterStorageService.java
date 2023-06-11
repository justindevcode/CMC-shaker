package com.example.Heunduljang.letter_storage.service;

import com.example.Heunduljang.invitation.entity.Invitation;
import com.example.Heunduljang.invitation.repository.InvitationRepository;
import com.example.Heunduljang.invitation.send_notice.FCMAcceptNotificationService;
import com.example.Heunduljang.letter_storage.dto.response.InvitationListResponseDto;
import com.example.Heunduljang.letter_storage.dto.response.InvitationResponseDto;
import com.example.Heunduljang.letter_storage.dto.response.UserByInvitationResponseDto;
import com.example.Heunduljang.user.entity.User;
import com.example.Heunduljang.user.repository.UserRepository;
import com.example.Heunduljang.user_invitation.entity.UserInvitation;
import com.example.Heunduljang.user_invitation.repository.UserInvitationRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LetterStorageService {

	private final UserRepository userRepository;
	private final InvitationRepository invitationRepository;

	private final UserInvitationRepository userInvitationRepository;

	private final FCMAcceptNotificationService fcmAcceptNotificationService;
	@Transactional
	public List<InvitationListResponseDto> getSendInvitationByUser(String appleId) {
		log.info("error");
		User user = userRepository.findByAppleId(appleId).orElse(null);
		log.info("error");
		if (user == null) {
			return null;
		}
		List<Invitation> invitations = invitationRepository.findByReceiverUser_AppleId(appleId);
		List<InvitationListResponseDto> invitationListResponseDtos = new ArrayList<>();
		for (Invitation invitation : invitations) {
			InvitationListResponseDto invitationListResponseDto = InvitationListResponseDto.builder()
				.invitationId(invitation.getInvitationId())
				.sendUserNickName(user.getNickname())
				.createdAt(invitation.getCreatedAt())
				.status(invitation.getStatus())
				.invitedCount(invitation.getInvitedCount())
				.comment(invitation.getComment())
				.build();
			invitationListResponseDtos.add(invitationListResponseDto);
		}
		return invitationListResponseDtos;
	}

	@Transactional
	public List<InvitationListResponseDto> getReceiveInvitationByUser(String appleId) {
		log.info("1error");
		User user = userRepository.findByAppleId(appleId).orElse(null);
		log.info("error");
		if (user == null) {
			return null;
		}
		List<UserInvitation> userInvitations = userInvitationRepository.findByReceiverUser(user);
		List<Invitation> invitations = new ArrayList<>();
		for (UserInvitation userInvitation : userInvitations) {
			List<Invitation> invitation = invitationRepository.findByReceiverUser(
				userInvitation.getCreatorUser());
			invitations.add(invitation.get(0));
		}
		List<InvitationListResponseDto> invitationListResponseDtos = new ArrayList<>();
		for (Invitation invitation : invitations) {
			InvitationListResponseDto invitationListResponseDto = InvitationListResponseDto.builder()
				.invitationId(invitation.getInvitationId())
				.sendUserNickName(user.getNickname())
				.createdAt(invitation.getCreatedAt())
				.status(invitation.getStatus())
				.invitedCount(invitation.getInvitedCount())
				.comment(invitation.getComment())
				.build();
			invitationListResponseDtos.add(invitationListResponseDto);
		}
		return invitationListResponseDtos;
	}

	@Transactional
	public InvitationResponseDto getInvitationByInvitationId(Long invitationId) {
		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		List<UserInvitation> userInvitations = userInvitationRepository.findByReceiverUser(invitation.getReceiverUser());
		List<UserByInvitationResponseDto> userByInvitationResponseDtos = new ArrayList<>();
		for (UserInvitation userInvitation : userInvitations) {
			if(userInvitation.isStatus()){
				UserByInvitationResponseDto user = UserByInvitationResponseDto.builder()
					.appleId(userInvitation.getReceiverUser().getAppleId())
					.userNickName(userInvitation.getReceiverUser().getNickname())
					.imageUrl(userInvitation.getReceiverUser().getNickname())
					.build();
				userByInvitationResponseDtos.add(user);
			}
		}
		return InvitationResponseDto.builder()
			.invitationId(invitation.getInvitationId())
			.sendUserNickName(invitation.getReceiverUser().getNickname())
			.createdAt(invitation.getCreatedAt())
			.status(invitation.getStatus())
			.comment(invitation.getComment())
			.kakaoLink(invitation.getKakaoLink())
			.receiverUsers(userByInvitationResponseDtos)
			.build();
	}

	@Transactional
	public String patchInvitationFinish(Long invitationId) {
		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		invitation.setStatus(true);
		return "Success invitationId : " + invitationId + "InvitationFinish";
	}

	@Transactional
	public String patchInvitationEnter(Long invitationId, String appleId) {
		User user = userRepository.findByAppleId(appleId).orElse(null);
		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);

		UserInvitation userInvitation = userInvitationRepository.findByInvitationAndReceiverUser(
				invitation, user)
			.orElse(null);
		userInvitation.setStatus(true);
		List<UserInvitation> userInvitations = userInvitationRepository.findByReceiverUser(invitation.getReceiverUser());
		int count = 0;
		for (UserInvitation userInvitation1 : userInvitations) {
			if(userInvitation1.isStatus()){
				count++;
			}
		}
		if (Integer.parseInt(invitation.getPersonnel()) == count) {
			invitation.setStatus(true);
		}
		fcmAcceptNotificationService.sendCreateNotificationByUser(invitation.getReceiverUser(),
			user);
		return "Success invitationId : " + invitationId + "Enter";

	}

}
