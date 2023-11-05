package com.julook.domain.home.find.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentInfoDTO {
    private String userNickName;
    private String userLikeOrNot;
    private String writeDate;
}
