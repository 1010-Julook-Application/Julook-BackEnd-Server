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
}
