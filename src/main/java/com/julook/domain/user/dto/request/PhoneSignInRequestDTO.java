package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhoneSignInRequestDTO {
    private String userNickName;
    private String userPhone;
    private String userBirth;
    private String userSex;
}
