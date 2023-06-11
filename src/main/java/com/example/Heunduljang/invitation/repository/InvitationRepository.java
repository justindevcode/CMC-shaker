package com.example.Heunduljang.invitation.repository;

import com.example.Heunduljang.invitation.entity.Invitation;
import com.example.Heunduljang.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

	List<Invitation> findByReceiverUser_AppleId(String appleId);

	List<Invitation> findByReceiverUser(User user);
}
