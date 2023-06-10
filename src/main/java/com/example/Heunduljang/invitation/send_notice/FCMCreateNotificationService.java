package com.example.Heunduljang.invitation.send_notice;

import com.example.Heunduljang.home.service.HomeService;
import com.example.Heunduljang.user.entity.User;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;


@Slf4j
@RequiredArgsConstructor
@Service
public class FCMCreateNotificationService {

	private final HomeService homeService;
	private final FirebaseMessaging firebaseMessaging;

	public String sendCreateNotificationByUser(User user) {
		List<User> users = homeService.findUsersWithinRadius(user.getAppleId());
		for (User userIter : users) {
			if(!userIter.isNotice()){
				continue;
			}
			Notification notification = Notification.builder()
				.setTitle("주변의 유저가 초대장을 보냈습니다!")
				.setBody(user.getNickname() + "님의 초대 참가하기!")
				.build();
			Message message = Message.builder()
				.setToken(userIter.getDeviceId())
				.setNotification(notification)
				.build();

			try {
				firebaseMessaging.send(message);
			} catch (FirebaseMessagingException e) {
				e.printStackTrace();
				return "알림 보내기를 실패하였습니다. targetUserId=" + userIter.getId();
			}
		}

		return "모든 알림을 성공적으로 전송 했습니다. createUserId = " + user.getId();
	}
}
