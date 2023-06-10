package com.example.Heunduljang.letter_storage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserByInvitationResponseDto {

	private String appleId;

	private String userNickName;

	private String imageUrl;
}
