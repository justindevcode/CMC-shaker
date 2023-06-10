package com.example.Heunduljang.letter_storage.service;

import com.example.Heunduljang.user.repository.UserRepository;
import com.example.Heunduljang.user_invitation.repository.UserInvitationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LetterStorageService {

	private final UserRepository userRepository;
	private final UserInvitationRepository userInvitationRepository;
	

}
