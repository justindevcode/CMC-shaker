package com.example.Heunduljang.letter_storage.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SendInvitation {

	private Long invitationId;

	private String sendUserNickName;

}
