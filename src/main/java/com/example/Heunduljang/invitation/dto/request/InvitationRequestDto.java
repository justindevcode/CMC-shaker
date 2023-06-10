package com.example.Heunduljang.invitation.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvitationRequestDto {

    @NotBlank(message = "인원이 존재하지 않습니다.")
    private String personnel;

    @NotBlank(message = "컨셉이 존재하지 않습니다.")
    private String concept;

    @NotBlank(message = "초대 이유가 존재하지 않습니다.")
    private String reason;

    @NotBlank(message = "카카오 링크가 존재하지 않습니다.")
    private String kakaoLink;

}
