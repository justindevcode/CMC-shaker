package com.example.Heunduljang.user.service;

import com.example.Heunduljang.common.exception.NotFoundException;
import com.example.Heunduljang.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.Heunduljang.common.error.ErrorCode.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String appleId) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByAppleId(appleId).orElseThrow(
                () -> new NotFoundException(USER_NOT_FOUND));
    }
}
