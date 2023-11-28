package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.LinkAccountRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.dto.request.PhoneSignInRequestDTO;
import com.julook.domain.user.dto.request.SMSRequestDTO;
import com.julook.domain.user.entity.User;

import java.util.List;

public interface ProfileRepositoryCustom{
    // 닉네임 수정
    Boolean modifyUserNickname(ModifyNickRequestDTO userRequest);

    // 회원 탈퇴
    Boolean deleteUserAccount(Long userId);

    // 기존 계정 찾기
    User findExistsAccount(LinkAccountRequestDTO userRequest);


    User findExistsAccount(PhoneSignInRequestDTO userRequest);


    // SMS 인증 후 기존 계정이 존재하는 지 확인
    List<User> findExistsAccount(SMSRequestDTO userRequest);

    // 데이터 연동 여부
    Boolean linkAccount(User userAccount, LinkAccountRequestDTO userRequest);
}
