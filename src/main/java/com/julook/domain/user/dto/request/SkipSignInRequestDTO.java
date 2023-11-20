package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SkipSignInRequestDTO {
    private String userNickName;
    private String userSex;
    private String userAgeRange;
}
