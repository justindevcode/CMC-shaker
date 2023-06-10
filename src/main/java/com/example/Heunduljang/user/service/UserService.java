package com.example.Heunduljang.user.service;

import com.example.Heunduljang.common.exception.BadRequestException;
import com.example.Heunduljang.user.dto.request.SignUpRequestDto;
import com.example.Heunduljang.user.dto.request.UserInfoRequestDto;
import com.example.Heunduljang.user.dto.response.SignUpResponseDto;
import com.example.Heunduljang.user.dto.response.UserInfoResponseDto;
import com.example.Heunduljang.user.entity.User;
import com.example.Heunduljang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.Heunduljang.common.error.ErrorCode.DUPLICATED_NICKNAME;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public boolean isValidAppleId(String appleId){
        return userRepository.findByAppleId(appleId).isPresent();
    }

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto){
        // 닉네임 중복검사
        userRepository.findByNickname(signUpRequestDto.getNickname())
                .ifPresent(user -> {
                    throw new BadRequestException(DUPLICATED_NICKNAME);
                });

        User user = User.builder()
                .appleId(signUpRequestDto.getAppleId())
                .age(signUpRequestDto.getAge())
                .gender(signUpRequestDto.getGender())
                .nickname(signUpRequestDto.getNickname())
                .deviceId(signUpRequestDto.getDeviceId())
                .build();

        userRepository.save(user);

        return new SignUpResponseDto(signUpRequestDto.getAppleId());
    }

    @Transactional
    public String changeNotice(String appleId) {
        User user = userRepository.findByAppleId(appleId).orElse(null);
        if (user.isNotice()) {
            user.setNotice(false);
        } else {
            user.setNotice(true);
        }
        return "Success appleId = " + appleId+ "notice is" + user.isNotice();
    }

    @Transactional
    public UserInfoResponseDto getUserIndo(String appleId) {
        User user = userRepository.findByAppleId(appleId).orElse(null);
        UserInfoResponseDto userInfoResponseDto = UserInfoResponseDto.builder()
            .appleId(user.getAppleId())
            .userNickName(user.getNickname())
            .imageUrl(user.getProfileImage())
            .notice(user.isNotice())
            .gender(user.getGender())
            .age(user.getAge())
            .build();
        return userInfoResponseDto;
    }

    @Transactional
    public UserInfoResponseDto patchUserInfo(String appleId, UserInfoRequestDto userInfoRequestDto) {
        userRepository.findByNickname(userInfoRequestDto.getNickname())
            .ifPresent(user -> {
                throw new BadRequestException(DUPLICATED_NICKNAME);
            });
        User user = userRepository.findByAppleId(appleId).orElse(null);

        user.setNickname(userInfoRequestDto.getNickname());
        user.setProfileImage(userInfoRequestDto.getImageUrl());
        user.setGender(userInfoRequestDto.getGender());
        user.setAge(userInfoRequestDto.getAge());

        UserInfoResponseDto userInfoResponseDto = UserInfoResponseDto.builder()
            .appleId(user.getAppleId())
            .userNickName(user.getNickname())
            .imageUrl(user.getProfileImage())
            .notice(user.isNotice())
            .gender(user.getGender())
            .age(user.getAge())
            .build();
        return userInfoResponseDto;

    }
}
