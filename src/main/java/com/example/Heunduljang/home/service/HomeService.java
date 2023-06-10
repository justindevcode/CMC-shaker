package com.example.Heunduljang.home.service;
import com.example.Heunduljang.home.dto.request.UserLocationRequestDto;
import com.example.Heunduljang.user.entity.User;
import com.example.Heunduljang.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeService {

	private final UserRepository userRepository;

	@Transactional
	public String putUserLocation(UserLocationRequestDto userLocationRequestDto) {
		User user = userRepository.findByAppleId(userLocationRequestDto.getAppleId()).orElse(null);
		if (user == null) {
			return null;
		}
		user.setLatitude(userLocationRequestDto.getLatitude());
		user.setLongitude(user.getLongitude());
		return "success userId : " + user.getId() + " - putLocation";
	}

	@Transactional
	public List<User> findUsersWithinRadius(String appleId) {
		double radius = 2.0; // 반경 2km
		double earthRadius = 6371; // 지구 반경 (단위: km)
		User user = userRepository.findByAppleId(appleId).orElse(null);
		if (user == null || user.getLatitude() == 0.0) {
			return null;
		}

		double latitude = user.getLatitude();
		double longitude = user.getLongitude();

		// 주어진 유저의 위도와 경도를 기준으로 2km 내의 위도 경도 범위 계산
		double dLatitude = radius / earthRadius;
		double dLongitude = radius / (earthRadius * Math.cos(Math.toRadians(latitude)));

		double minLatitude = latitude - dLatitude;
		double maxLatitude = latitude + dLatitude;
		double minLongitude = longitude - dLongitude;
		double maxLongitude = longitude + dLongitude;

		// 스프링 Data JPA를 사용하여 위도 경도 범위 내의 유저 조회
		return userRepository.findByLatitudeBetweenAndLongitudeBetween(minLatitude, maxLatitude, minLongitude, maxLongitude);
	}
}



