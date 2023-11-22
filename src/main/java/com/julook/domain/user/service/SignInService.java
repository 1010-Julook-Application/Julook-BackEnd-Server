package com.julook.domain.user.service;

import com.julook.domain.user.dto.response.SkipSignInResponseDTO;

public interface SignInService {
    // 아이디 중복 여부 확인
    Boolean isUserIdDuplicate(Long userID);

    // 랜덤 아이디 생성
    Long generateUniqueRandomId();

    // 회원 가입 성공 여부 전달
    SkipSignInResponseDTO registerUserResults(Long userId, String nickName, String gender, String ageGroup);
}
