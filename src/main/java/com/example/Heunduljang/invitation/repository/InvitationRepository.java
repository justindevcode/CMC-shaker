package com.example.Heunduljang.invitation.repository;

import com.example.Heunduljang.invitation.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
