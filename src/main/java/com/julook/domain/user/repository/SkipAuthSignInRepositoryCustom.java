package com.julook.domain.user.repository;

import com.julook.domain.user.entity.User;

public interface SkipAuthSignInRepositoryCustom {

    // 중복 아이디 확인
    User findByUserID(Long userId);

    // 아이디 생성 시, 성별 나이대 닉네임 넣기
    Boolean setUserInfo(Long userId, String nickName, String gender, String ageGroup);


}
