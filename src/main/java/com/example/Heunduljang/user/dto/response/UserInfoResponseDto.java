package com.example.Heunduljang.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserInfoResponseDto {

	private String appleId;

	private String userNickName;

	private String imageUrl;

	private boolean notice;

	private String gender;

	private String age;
}
