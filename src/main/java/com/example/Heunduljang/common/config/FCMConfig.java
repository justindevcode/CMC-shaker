package com.example.Heunduljang.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Slf4j
public class FCMConfig {

	@Bean
	FirebaseMessaging firebaseMessaging() throws IOException {
		log.info("FCMconfig Start");
		ClassPathResource resource = new ClassPathResource(
			"firebase/heunduljang-d6339-firebase-adminsdk-v2xts-79b24704f3.json");

		InputStream refreshToken = resource.getInputStream();
		FirebaseApp firebaseApp = null;
		List<FirebaseApp> firebaseAppList = FirebaseApp.getApps();

		if (firebaseAppList != null && !firebaseAppList.isEmpty()) {
			for (FirebaseApp app : firebaseAppList) {
				if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
					firebaseApp = app;
				}
			}
		} else {
			FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(refreshToken))
				.build();
			firebaseApp = FirebaseApp.initializeApp(options);
		}

		return FirebaseMessaging.getInstance(firebaseApp);
	}

}
