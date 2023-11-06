package com.julook.domain.home.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfoDTO {
    private String userNickName;
    private Character userLikeOrNot;
    private String contents;
    private LocalDateTime writeDate;
}
