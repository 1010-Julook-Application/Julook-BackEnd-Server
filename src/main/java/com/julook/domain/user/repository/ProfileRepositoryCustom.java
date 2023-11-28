package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.*;
import com.julook.domain.user.entity.User;

import java.util.List;

public interface ProfileRepositoryCustom{
    // 닉네임 수정
    Boolean modifyUserNickname(ModifyNickRequestDTO userRequest);

    // 회원 탈퇴
    Boolean deleteUserAccount(Long userId);

//    // 기존 계정 찾기
    User findExistsAccount(LinkAccountRequestDTO userRequest);

//    User findExistsAccount(PhoneSignInRequestDTO userRequest);


    User findExistsAccount(CheckAccountRequestDTO userRequest);
    // 데이터 연동 여부
    Boolean linkAccount(User userAccount, LinkAccountRequestDTO userRequest);
}
