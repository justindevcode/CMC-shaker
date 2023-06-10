package com.example.Heunduljang.invitation.entity;


import com.example.Heunduljang.common.base.BaseEntity;
import com.example.Heunduljang.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "invitation")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Invitation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invitation_id")
    private Long invitationId;

//    @Column(name = "receiver_user",nullable = false)
    @OneToOne
    private User receiverUser;

    @Column(name = "comment")
    private String comment;

    @Column(name = "kakao_link")
    private String kakaoLink;

    @Column(name = "concept")
    private String concept;

    @Column(name = "reason")
    private String reason;

    @Column(name = "personnel")
    private String personnel;

    @Column(name = "invited_count")
    private int invitedCount;

    @Column(name = "status")
    private Boolean status;



}
