package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentRequestDTO {
    private UUID commentId;
    private Long userId;
    private int makNumber;
    private String contents;
    private Character isVisible;
    private Character isUserDeleteComment;

    // 댓글 처음 달 때, insert
    public CommentRequestDTO(Long userId, int makNumber, String contents, char isVisible) {
        this.userId = userId;
        this.makNumber = makNumber;
        this.contents = contents;
        this.isVisible = isVisible;
    }

    // 댓글 수정 할 때, update
    public CommentRequestDTO(UUID commentId, String contents, char isVisible) {
        this.commentId = commentId;
        this.contents = contents;
        this.isVisible = isVisible;
    }

    // 댓글 삭제할 때, delete
    public CommentRequestDTO(UUID commentId, char isUserDeleteComment) {
        this.commentId = commentId;
        this.isUserDeleteComment = isUserDeleteComment;
    }


}
