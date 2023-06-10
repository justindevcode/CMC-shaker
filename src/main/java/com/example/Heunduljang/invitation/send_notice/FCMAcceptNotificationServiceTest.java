package com.example.Heunduljang.invitation.send_notice;

import com.example.Heunduljang.user.entity.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FCMAcceptNotificationServiceTest {

	private final FirebaseMessaging firebaseMessaging;

	public String sendCreateNotificationByUser() {

			Notification notification = Notification.builder()
				.setTitle("주변의 유저가 초대를 수락했습니다!")
				.setBody("a testuser" + "님이 참가하셨습니다!")
				.build();
			Message message = Message.builder()
				.setToken("dkjmVPjeOk7bhiPfbVuJSM:APA91bF-SwQimyN9SkVh5SuWNkA4-mC3a0sMRG_95S5gAmLU8i-RZqOTzFTn5PDh6Sme72r2a2yf2BqujpXpzk-h5dYQdaHJiDJ6P9zx0SKv2ivBKd4yyCXUXop4IKvGCijMnbD-WWeK")
				.setNotification(notification)
				.build();

			try {
				return firebaseMessaging.send(message);
			} catch (FirebaseMessagingException e) {
				e.printStackTrace();
				return "알림 보내기를 실패하였습니다. ";
			}
	}
}
