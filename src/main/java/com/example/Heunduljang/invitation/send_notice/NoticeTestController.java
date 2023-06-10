package com.example.Heunduljang.invitation.send_notice;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "알림 테스트")
@RestController
@RequiredArgsConstructor
@RequestMapping("/notice_test")
public class NoticeTestController {

	private final FCMAcceptNotificationServiceTest fcmAcceptNotificationServiceTest;


	@GetMapping
	public ResponseEntity<String> noticeTest() {
		String s = fcmAcceptNotificationServiceTest.sendCreateNotificationByUser();
		return new ResponseEntity<>(s, new HttpHeaders(HttpHeaders.EMPTY), HttpStatus.ACCEPTED);
	}
}
