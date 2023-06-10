package com.example.Heunduljang.invitation.send_notice;

import static org.junit.jupiter.api.Assertions.*;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
class FCMAcceptNotificationServiceTest {


	private FirebaseMessaging firebaseMessaging;

	@Autowired
	public FCMAcceptNotificationServiceTest(FirebaseMessaging firebaseMessaging) {
		this.firebaseMessaging = firebaseMessaging;
	}


	@Test
	void sendCreateNotificationByUser() {
		Notification notification = Notification.builder()
			.setTitle("주변의 유저가 초대를 수락했습니다!")
			.setBody("acceptUserTest" + "님이 참가하셨습니다!")
			.build();
		Message message = Message.builder()
			.setToken("dummy_device_token")
			.setNotification(notification)
			.build();

		try {
			String response = firebaseMessaging.send(message);
			System.out.println("푸시 알림 전송 결과: " + response);
		} catch (FirebaseMessagingException e) {
			e.printStackTrace();
		}

		System.out.println("성공");
	}
}