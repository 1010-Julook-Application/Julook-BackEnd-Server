package com.julook.domain.user.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.SkipSignInResponseDTO;
import com.julook.domain.user.dto.response.WishResponseDTO;
import com.julook.domain.user.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserActionController {
    // 서비스 선언
    private final UserActionService userActionService;

    @Autowired
    public UserActionController(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    // 찜 하기
    @PostMapping("/addWishList")
    public ResponseEntity<ApiResponseDTO<WishResponseDTO>> addMakUserWishList(
            @RequestBody WishRequestDTO userRequest) {

        // 서비스 메서드
        WishResponseDTO addWishResults = userActionService.addWishList(userRequest);

        ApiResponseDTO<WishResponseDTO> response = ApiResponseDTO.<WishResponseDTO>builder()
                .resultCode(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(addWishResults)
                .build();

        return ResponseEntity.ok(response);
    }

    // 찜 삭제
    @PostMapping("/deleteWishList")
    public ResponseEntity<ApiResponseDTO<WishResponseDTO>> deleteMakUserWishList(
            @RequestBody WishRequestDTO userRequest) {

        // 서비스 메소드
        WishResponseDTO deleteResults = userActionService.deleteWishList(userRequest);

        ApiResponseDTO<WishResponseDTO> response = ApiResponseDTO.<WishResponseDTO>builder()
                .resultCode(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(deleteResults)
                .build();

        return ResponseEntity.ok(response);
    }
}
