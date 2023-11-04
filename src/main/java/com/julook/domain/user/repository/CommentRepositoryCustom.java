package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.entity.Comment;

import java.util.UUID;

public interface CommentRepositoryCustom {
    // 코멘트 작성 - insert
    UUID insertUserComment(CommentRequestDTO userRequest);
    // 코멘트 수정 - update
    Boolean updateUserComment(CommentRequestDTO userRequest);
    // 코멘트 삭제 - delete
    Boolean deleteUserComment(CommentRequestDTO userRequest);
}
