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
    private Integer numberOfAccount;  // 조회된 계정 개수
    private List<String> userNickName;  // 닉네임 개수

}
