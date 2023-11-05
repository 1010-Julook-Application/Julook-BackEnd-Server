package com.julook.domain.user.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private UUID commentId;
    private Boolean isSuccess;
    private String message;
}
