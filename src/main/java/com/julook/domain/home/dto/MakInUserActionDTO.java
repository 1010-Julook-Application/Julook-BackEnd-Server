package com.julook.domain.home.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakInUserActionDTO {
    private String isAddedInUserWishList;
    private String isUserEvaluate;
    private String userEvaluateValue;
    private String isInMyComment;
    private String commentContents;
    private Character isCommentVisible;
    private LocalDateTime writeDate;
}
