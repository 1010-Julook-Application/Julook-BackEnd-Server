package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.entity.User;

public interface ProfileRepositoryCustom {
    // 닉네임 수정
    Boolean modifyUserNickname(ModifyNickRequestDTO userRequest);

    // 회원 탈퇴
    Boolean deleteUserAccount(Long userId);
}
