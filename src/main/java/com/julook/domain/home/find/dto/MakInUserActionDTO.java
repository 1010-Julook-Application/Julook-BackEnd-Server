package com.julook.domain.home.find.dto;

import lombok.*;

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
    private UUID commentId;
    private String commentContents;
    private Character isCommentVisible;
}
