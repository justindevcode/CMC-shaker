package com.example.Heunduljang.letter_storage.dto.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class InvitationListResponseDto {

	private Long invitationId;

	private String sendUserNickName;

	private LocalDateTime createdAt;

	private Boolean status;

	private int invitedCount;

	private String comment;

}
