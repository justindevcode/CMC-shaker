package com.example.Heunduljang.invitation.send_notice;

import com.example.Heunduljang.user.entity.User;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FCMAcceptNotificationService {

	private final FirebaseMessaging firebaseMessaging;

	public String sendCreateNotificationByUser(User createUser, User acceptUser) {
		if(!acceptUser.isNotice()){
			return "알림을 수신하지 않는 유저입니다.";
		}

			Notification notification = Notification.builder()
				.setTitle("주변의 유저가 초대를 수락했습니다!")
				.setBody(acceptUser.getNickname() + "님이 참가하셨습니다!")
				.build();
			Message message = Message.builder()
				.setToken(createUser.getDeviceId())
				.setNotification(notification)
				.build();

			try {
				firebaseMessaging.send(message);
			} catch (FirebaseMessagingException e) {
				e.printStackTrace();
				return "알림 보내기를 실패하였습니다. targetUserId=" + createUser.getId();
			}

		return "알림을 성공적으로 전송 했습니다. createUserId = " + createUser.getId();
	}
}
