package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.CommentResponseDTO;
import com.julook.domain.user.dto.response.EvaluateMakResponseDTO;
import com.julook.domain.user.dto.response.UserActionResponseDTO;
import com.julook.domain.user.entity.User;

public interface UserActionService {
    // 찜 추가
    UserActionResponseDTO addWishList(WishRequestDTO userRequest);

    // 찜 삭제
    UserActionResponseDTO deleteWishList(WishRequestDTO userRequest);

    // 막걸리 평가
    UserActionResponseDTO evaluateMak(EvaluateMakRequestDTO userRequest);

    // 코멘트 달기 - insert
    CommentResponseDTO insertUserComment(CommentRequestDTO userRequest);

    // 코멘트 수정 - update
    UserActionResponseDTO updateUserComment(CommentRequestDTO userRequest);

    // 코멘트 삭제 - delete
    UserActionResponseDTO deleteUserComment(CommentRequestDTO userRequest);
}
