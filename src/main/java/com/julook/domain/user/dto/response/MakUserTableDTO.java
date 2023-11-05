package com.julook.domain.user.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakUserTableDTO {
    private Long usrId;

    private int makSeq;
    private String makNm;
    private String makImg;
    private String reactionLike;
    private String reactionWish;
    private String reactionComment;
    private LocalDateTime reactionLikeDate;
    private LocalDateTime reactionWishDate;
    private LocalDateTime reactionCommentDate;
}
