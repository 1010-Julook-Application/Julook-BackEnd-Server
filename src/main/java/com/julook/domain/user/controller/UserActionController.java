package com.julook.domain.user.controller;

import com.julook.domain.common.dto.response.ApiResponseDTO;
import com.julook.domain.home.find.dto.response.FindByUserResponseDTO;
import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.CommentResponseDTO;
import com.julook.domain.user.dto.response.UserActionResponseDTO;
import com.julook.domain.user.dto.response.UserMakFolderResponseDTO;
import com.julook.domain.user.service.UserActionService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponseDTO<UserActionResponseDTO>> addMakUserWishList(
            @RequestBody WishRequestDTO userRequest) {

        // 서비스 메서드
        UserActionResponseDTO addWishResults = userActionService.addWishList(userRequest);

        ApiResponseDTO<UserActionResponseDTO> response = ApiResponseDTO.<UserActionResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(addWishResults)
                .build();

        return ResponseEntity.ok(response);
    }

    // 찜 삭제
    @PostMapping("/deleteWishList")
    public ResponseEntity<ApiResponseDTO<UserActionResponseDTO>> deleteMakUserWishList(
            @RequestBody WishRequestDTO userRequest) {

        // 서비스 메소드
        UserActionResponseDTO deleteResults = userActionService.deleteWishList(userRequest);

        ApiResponseDTO<UserActionResponseDTO> response = ApiResponseDTO.<UserActionResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(deleteResults)
                .build();

        return ResponseEntity.ok(response);
    }

    // 막걸리 평가 - 좋았어요, 아쉬워요
    @PostMapping("/evaluateMak")
    public ResponseEntity<ApiResponseDTO<UserActionResponseDTO>> evaluateMak(
            @RequestBody EvaluateMakRequestDTO userRequest) {

        UserActionResponseDTO evaluateResults = userActionService.evaluateMak(userRequest);

        ApiResponseDTO<UserActionResponseDTO> response = ApiResponseDTO.<UserActionResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(evaluateResults)
                .build();

        return ResponseEntity.ok(response);

    }

    // 코멘트 작성 - Insert
    @PostMapping("/insertComment")
    public ResponseEntity<ApiResponseDTO<CommentResponseDTO>> insertUserComment(
            @RequestBody CommentRequestDTO userRequest) {
        CommentResponseDTO insertResults = userActionService.insertUserComment(userRequest);

        ApiResponseDTO<CommentResponseDTO> response = ApiResponseDTO.<CommentResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(insertResults)
                .build();

        return ResponseEntity.ok(response);
    }


    // 코멘트 수정 - Update
    @PostMapping("/updateComment")
    public ResponseEntity<ApiResponseDTO<UserActionResponseDTO>> updateUserComment(
            @RequestBody CommentRequestDTO userRequest) {

        UserActionResponseDTO updateResults = userActionService.updateUserComment(userRequest);

        ApiResponseDTO<UserActionResponseDTO> response = ApiResponseDTO.<UserActionResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(updateResults)
                .build();

        return ResponseEntity.ok(response);
    }


    // 코멘트 삭제 - Delete
    @PostMapping("/deleteComment")
    public ResponseEntity<ApiResponseDTO<UserActionResponseDTO>> deleteUserComment(
            @RequestBody CommentRequestDTO userRequest) {
        UserActionResponseDTO deleteResults = userActionService.deleteUserComment(userRequest);

        ApiResponseDTO<UserActionResponseDTO> response = ApiResponseDTO.<UserActionResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(deleteResults)
                .build();

        return ResponseEntity.ok(response);
    }


    // 사용자 찜 폴더 조회 - select
    @GetMapping("/getUserMakFolder")
    public ResponseEntity<ApiResponseDTO<UserMakFolderResponseDTO>> getUserMakFolder(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "segmentName", defaultValue = "entire") String category,
            @RequestParam(value = "lastMakNum", defaultValue = "300000") int lastMakNum,
            @PageableDefault(size = 10) Pageable pageable) {


        UserMakFolderResponseDTO userFolderResponse = userActionService.getUserMakFolder(userId, category, lastMakNum, pageable);

        ApiResponseDTO<UserMakFolderResponseDTO> response = ApiResponseDTO.<UserMakFolderResponseDTO>builder()
                .status(HttpStatus.OK.value())
                .resultMsg(HttpStatus.OK.getReasonPhrase())
                .result(userFolderResponse)
                .build();

        return ResponseEntity.ok(response);

    }


}
