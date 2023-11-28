package com.julook.domain.user.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.user.dto.request.CheckAccountRequestDTO;
import com.julook.domain.user.dto.request.LinkAccountRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.dto.response.CheckAccountResponseDTO;
import com.julook.domain.user.dto.response.DeleteUserResponseDTO;
import com.julook.domain.user.dto.response.LinkAccountResponseDTO;
import com.julook.domain.user.dto.response.ModifyNickResponseDTO;
import com.julook.domain.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v2/account")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    // 사용자 닉네임 수정
    @PostMapping("/modifyUserNickname")
    public ResponseEntity<ApiResponseDTO<ModifyNickResponseDTO>> modifyUserNickName(
            @RequestBody ModifyNickRequestDTO userRequest) {
        ModifyNickResponseDTO modifiedResults = userAccountService.modifiyUserNickname(userRequest);

        ApiResponseDTO<ModifyNickResponseDTO> response = ApiResponseDTO.<ModifyNickResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(modifiedResults)
                .build();

        return ResponseEntity.ok(response);
    }


    // 사용자 탈퇴
    @PostMapping("/deleteUserAccount")
    public ResponseEntity<ApiResponseDTO<DeleteUserResponseDTO>> deleteUserAccount(
            @RequestBody Map<String, Long> requestBody) {
        Long userId = requestBody.get("userId");
        DeleteUserResponseDTO deleteResults = userAccountService.deleteUserAccount(userId);

        ApiResponseDTO<DeleteUserResponseDTO> response = ApiResponseDTO.<DeleteUserResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(deleteResults)
                .build();

        return ResponseEntity.ok(response);
    }

    // 데이터 연동 - 핸드폰번호 인증
    @PostMapping("/dataLinking")
    public ResponseEntity<ApiResponseDTO<LinkAccountResponseDTO>> linkExistsAccount(
            @RequestBody LinkAccountRequestDTO userRequest) {

        LinkAccountResponseDTO linkResults = userAccountService.linkUserAccount(userRequest);

        ApiResponseDTO<LinkAccountResponseDTO> response = ApiResponseDTO.<LinkAccountResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(linkResults)
                .build();

        return ResponseEntity.ok(response);
    }

    // 기존 회원 여부 확인
    @PostMapping("/findMatchAccount")
    public ResponseEntity<ApiResponseDTO<CheckAccountResponseDTO>> findMatchAccount(
            @RequestBody CheckAccountRequestDTO userRequest) {

        CheckAccountResponseDTO matchedResults = userAccountService.findMatchedAccount(userRequest);

        ApiResponseDTO<CheckAccountResponseDTO> response = ApiResponseDTO.<CheckAccountResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(matchedResults)
                .build();

        return ResponseEntity.ok(response);
    }
}
