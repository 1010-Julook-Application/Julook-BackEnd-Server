package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.*;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.entity.UserMakFolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    // 사용자 찜 막걸리 조회 - select
//    UserMakFolderResponseDTO getUserMakFolder(Long userId, String segmentName, int lastMakNum, Pageable pageable);
    Page<MakUserTableDTO> getUserMakFolder(Long userId, String segmentName, int offset, int pageSize);


}
