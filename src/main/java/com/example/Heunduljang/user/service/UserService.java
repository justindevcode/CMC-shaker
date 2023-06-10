package com.example.Heunduljang.user.service;

import com.example.Heunduljang.common.base.BaseException;
import com.example.Heunduljang.common.exception.BadRequestException;
import com.example.Heunduljang.user.dto.request.SignUpRequestDto;
import com.example.Heunduljang.user.dto.response.SignUpResponseDto;
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
}
