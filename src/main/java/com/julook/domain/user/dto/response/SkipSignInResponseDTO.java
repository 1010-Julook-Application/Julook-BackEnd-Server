package com.julook.domain.user.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkipSignInResponseDTO {
    private Long userID;
    private String userNickName;
    private String userSex;
    private String userAgeRange;
    private String isUserVerified;
    private String userJoinDate;
}
