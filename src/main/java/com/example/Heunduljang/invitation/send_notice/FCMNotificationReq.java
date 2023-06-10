package com.example.Heunduljang.invitation.send_notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FCMNotificationReq {

	private Long userId;
	private String title;
	private String body;


}
