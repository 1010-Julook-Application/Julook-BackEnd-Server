package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.PhoneSignInRequestDTO;
import com.julook.domain.user.dto.response.SignInResponseDTO;
import com.julook.domain.user.dto.response.UserActionResponseDTO;

public interface SignInService {
    // 아이디 중복 여부 확인
    Boolean isUserIdDuplicate(Long userID);

    // 닉네임 중복 여부
    UserActionResponseDTO isUserNickNameDuplicate(String nickName);

    // 랜덤 아이디 생성
    Long generateUniqueRandomId();

    // 회원 가입 성공 여부 전달 - 건너뛰기 가입 시
    SignInResponseDTO registerUserResults(Long userId, String nickName, String gender, String ageGroup);

    // 폰 인증 가입 시 회원 가입 성공 여부
    SignInResponseDTO registerWithPhoneUserResults(Long userId, PhoneSignInRequestDTO userRequest);



}
