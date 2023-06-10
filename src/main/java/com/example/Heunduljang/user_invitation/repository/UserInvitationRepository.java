package com.example.Heunduljang.user_invitation.repository;

import com.example.Heunduljang.invitation.entity.Invitation;
import com.example.Heunduljang.user.entity.User;
import com.example.Heunduljang.user_invitation.entity.UserInvitation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInvitationRepository extends JpaRepository<UserInvitation, Long> {

	List<UserInvitation> findByReceiverUser(User user);

	Optional<UserInvitation> findByInvitationAndReceiverUser(Invitation invitation,User user);

}
