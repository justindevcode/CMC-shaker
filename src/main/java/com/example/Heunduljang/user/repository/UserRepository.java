package com.example.Heunduljang.user.repository;

import com.example.Heunduljang.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByAppleId(String AppleId);
    Optional<User> findByNickname(String nickname);


}
