package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.LinkAccountRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.dto.response.DeleteUserResponseDTO;
import com.julook.domain.user.dto.response.LinkAccountResponseDTO;
import com.julook.domain.user.dto.response.ModifyNickResponseDTO;

public interface UserAccountService {
    // 사용자 닉네임 수정 - update
    ModifyNickResponseDTO modifiyUserNickname(ModifyNickRequestDTO userRequest);

    // 사용자 탈퇴
    DeleteUserResponseDTO deleteUserAccount(Long userId);

    // 사용자 데이터 연동
    LinkAccountResponseDTO linkUserAccount(LinkAccountRequestDTO userRequest);
}
