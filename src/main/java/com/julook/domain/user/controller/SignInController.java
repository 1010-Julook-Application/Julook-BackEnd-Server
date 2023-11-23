package com.julook.domain.user.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.user.dto.request.PhoneSignInRequestDTO;
import com.julook.domain.user.dto.request.SkipSignInRequestDTO;
import com.julook.domain.user.dto.response.SignInResponseDTO;
import com.julook.domain.user.dto.response.UserActionResponseDTO;
import com.julook.domain.user.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class SignInController {
    // 서비스 선언
    private final SignInService signInService;

    @Autowired
    public SignInController(SignInService skipAuthSignInService) {
        this.signInService = skipAuthSignInService;
    }

    // 건너뛰기 가입 시
    @PostMapping("/SkipSignIn")
    public ResponseEntity<ApiResponseDTO<SignInResponseDTO>> generateUserInfo(
            @RequestBody SkipSignInRequestDTO userInfo) {

        // 닉네임, 성별, 나이대 가져오기
        String nickName = userInfo.getUserNickName();
        String gender = userInfo.getUserSex();
        String ageGroup = userInfo.getUserAgeRange();

        // 랜덤 아이디 생성
        Long userID = signInService.generateUniqueRandomId();

        // 서비스 메서드 호출
        SignInResponseDTO generateResults = signInService.registerUserResults(userID, nickName, gender, ageGroup);

        ApiResponseDTO<SignInResponseDTO> response = ApiResponseDTO.<SignInResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(generateResults)
                .build();

        return ResponseEntity.ok(response);
    }

    // phone 인증 가입 시
    @PostMapping("/phoneSignIn")
    public ResponseEntity<ApiResponseDTO<SignInResponseDTO>> generatePhoneAuthUserInfo(
            @RequestBody PhoneSignInRequestDTO userRequest) {

        // 랜덤 아이디 생성
        Long userID = signInService.generateUniqueRandomId();

        // 서비스 호출
        SignInResponseDTO phoneSignIn = signInService.registerWithPhoneUserResults(userID, userRequest);

        ApiResponseDTO<SignInResponseDTO> response = ApiResponseDTO.<SignInResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(phoneSignIn)
                .build();

        return ResponseEntity.ok(response);
    }



    @GetMapping("/checkNickname/{nickname}")
    public ResponseEntity<ApiResponseDTO<UserActionResponseDTO>> isUserNickNameDuplicated(
            @PathVariable String nickname
    ) {
        UserActionResponseDTO checkUserNick = signInService.isUserNickNameDuplicate(nickname);

        ApiResponseDTO<UserActionResponseDTO> response = ApiResponseDTO.<UserActionResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(checkUserNick)
                .build();

        return ResponseEntity.ok(response);
    }
}
