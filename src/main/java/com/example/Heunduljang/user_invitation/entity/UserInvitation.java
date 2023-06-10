package com.example.Heunduljang.user_invitation.entity;

import com.example.Heunduljang.common.base.BaseEntity;
import com.example.Heunduljang.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "user_invitation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UserInvitation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_invitation_id")
    private Long userInvitationId;

    @ManyToOne
    @JoinColumn(name = "creator_user_id")
    private User creatorUser;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private User receiverUser;

    @Column(name = "status")
    private boolean status;
}
