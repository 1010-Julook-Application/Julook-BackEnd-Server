package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.LinkAccountRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.dto.request.SMSRequestDTO;
import com.julook.domain.user.dto.response.CheckAccountResponseDTO;
import com.julook.domain.user.dto.response.DeleteUserResponseDTO;
import com.julook.domain.user.dto.response.LinkAccountResponseDTO;
import com.julook.domain.user.dto.response.ModifyNickResponseDTO;
import com.julook.domain.user.entity.User;

import java.util.List;

public interface UserAccountService {
    // 사용자 닉네임 수정 - update
    ModifyNickResponseDTO modifiyUserNickname(ModifyNickRequestDTO userRequest);

    // 사용자 탈퇴
    DeleteUserResponseDTO deleteUserAccount(Long userId);

    // 사용자 데이터 연동
    LinkAccountResponseDTO linkUserAccount(LinkAccountRequestDTO userRequest);

    // SMS 인증 후, 핸드폰 번호와 일치하는 계정 확인
    CheckAccountResponseDTO findMatchedAccount(SMSRequestDTO userRequest);
}
