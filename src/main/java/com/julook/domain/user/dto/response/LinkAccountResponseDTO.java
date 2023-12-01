package com.julook.domain.user.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccountResponseDTO {
    private UserActionResponseDTO linkedResults;
    private Boolean isAlreadyLinked;
    private Long userId;
    private String userNickname;
    private String phoneSuffix; // 뒷자리
    private String isUserVerified;
}
