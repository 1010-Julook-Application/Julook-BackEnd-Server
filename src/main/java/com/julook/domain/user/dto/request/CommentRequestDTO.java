package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CommentRequestDTO {
    private Long userId;
    private int makNumber;
    private String contents;
    private Character isVisible;
    private Character isUserDeleteComment;

    // 댓글 처음 insert, update 시
    public CommentRequestDTO(Long userId, int makNumber, String contents, char isVisible) {
        this.userId = userId;
        this.makNumber = makNumber;
        this.contents = contents;
        this.isVisible = isVisible;
    }


    // 댓글 삭제할 때, delete
    public CommentRequestDTO(Long userId, int makNumber, char isUserDeleteComment) {
        this.userId = userId;
        this.makNumber = makNumber;
        this.isUserDeleteComment = isUserDeleteComment;
    }


}
