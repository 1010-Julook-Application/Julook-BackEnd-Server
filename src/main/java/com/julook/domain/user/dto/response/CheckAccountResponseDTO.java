package com.julook.domain.user.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckAccountResponseDTO {
    private Boolean isAccountExisted;  // 계정 존재 여부
    SignInResponseDTO userResults;
}
