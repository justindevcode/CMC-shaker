package com.example.Heunduljang.letter_storage.dto.response;

import com.example.Heunduljang.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InvitationResponseDto {
	private Long invitationId;

	private String sendUserNickName;

	private LocalDateTime createdAt;

	private Boolean status;

	private String comment;

	private String kakaoLink;

	private List<UserByInvitationResponseDto> receiverUsers;

}
