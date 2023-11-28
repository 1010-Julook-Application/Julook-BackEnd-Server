package com.julook.domain.user.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDTO {
    private Boolean isAlreadyLinked;  // 이미 폰 연동했는 지 여부
    private Long userID;
    private String userNickName;
    private String userPhone;  // 전화번호 끝자리
    private String userBirth;
    private String userSex;
    private String userAgeRange;
    private String isUserVerified;
    private LocalDate userJoinDate;


    public static SignInResponseDTO phoneSignIn(Boolean isAlreadyLinked, Long userID, String userNickName, String userPhone, String userBirth,
                                                String userSex, String isUserVerified, String userJoinDate) {
        return SignInResponseDTO.builder()
                .isAlreadyLinked(isAlreadyLinked)
                .userID(userID)
                .userNickName(userNickName)
                .userPhone(userPhone)
                .userBirth(userBirth)
                .userSex(userSex)
                .isUserVerified(isUserVerified)
                .userJoinDate(LocalDate.parse(userJoinDate))
                .build();
    }

    public static SignInResponseDTO SkipSignIn(Long userID, String userNickName, String userSex,
                                               String userAgeRange, String isUserVerified, String userJoinDate) {
        return SignInResponseDTO.builder()
                .isAlreadyLinked(false)
                .userID(userID)
                .userNickName(userNickName)
                .userSex(userSex)
                .userAgeRange(userAgeRange)
                .isUserVerified(isUserVerified)
                .userJoinDate(LocalDate.parse(userJoinDate))
                .build();
    }

}
