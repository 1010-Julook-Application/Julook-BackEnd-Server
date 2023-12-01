package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.PhoneSignInRequestDTO;
import com.julook.domain.user.entity.User;

public interface SignInRepositoryCustom {

    // 중복 아이디 확인
    User findByUserID(Long userId);

    // 중복 닉네임 확인
    int findByUserNickName(String userNickName);

    // 아이디 생성 시, 성별 나이대 닉네임 넣기 ( 건너뛰기 가입 시)
    Boolean setUserInfo(Long userId, String nickName, String gender, String ageGroup);

    // 핸드폰번호 입력 가입 시, 랜덤 아이디, 핸드폰 번호, 생년월일 입력
    Boolean setUserInfoWithPhone(Long userID, PhoneSignInRequestDTO userRequest);

    // 로그인 시, 기존에 해당 아이디와 핸드폰번호가 있는 지를 확인
    Boolean findByUserIdWithPhoneAndBirth(String phone, String birth);

}
