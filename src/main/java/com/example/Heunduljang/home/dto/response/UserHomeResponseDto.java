package com.example.Heunduljang.home.dto.response;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserHomeResponseDto {

	private String appleId;

	private String userNickName;

	private String imageUrl;

	private double latitude;

	private double longitude;
}
