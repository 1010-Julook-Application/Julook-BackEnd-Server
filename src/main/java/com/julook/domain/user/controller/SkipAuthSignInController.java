package com.julook.domain.user.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.user.dto.request.SkipSignInRequestDTO;
import com.julook.domain.user.dto.response.SkipSignInResponseDTO;
import com.julook.domain.user.service.SkipAuthSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class SkipAuthSignInController {
    // 서비스 선언
    private final SkipAuthSignInService skipAuthSignInService;

    @Autowired
    public SkipAuthSignInController(SkipAuthSignInService skipAuthSignInService) {
        this.skipAuthSignInService = skipAuthSignInService;
    }

    @PostMapping("/SkipSignIn")
    public ResponseEntity<ApiResponseDTO<SkipSignInResponseDTO>> generateUserInfo(
            @RequestBody SkipSignInRequestDTO userInfo) {

        // 닉네임, 성별, 나이대 가져오기
        String nickName = userInfo.getUserNickName();
        String gender = userInfo.getUserSex();
        String ageGroup = userInfo.getUserAgeRange();

        // 랜덤 아이디 생성
        Long userID = skipAuthSignInService.generateUniqueRandomId();

        // 서비스 메서드 호출
        SkipSignInResponseDTO generateResults = skipAuthSignInService.registerUserResults(userID, nickName, gender, ageGroup);

        ApiResponseDTO<SkipSignInResponseDTO> response = ApiResponseDTO.<SkipSignInResponseDTO>builder()
                .resultCode(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(generateResults)
                .build();

        return ResponseEntity.ok(response);

    }

}
