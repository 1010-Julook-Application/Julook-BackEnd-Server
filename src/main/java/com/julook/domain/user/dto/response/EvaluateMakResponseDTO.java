package com.julook.domain.user.dto.response;

import lombok.*;

import java.security.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluateMakResponseDTO {
    private Boolean isSuccess;
    private String message;
//    private Timestamp evaluateDate;
}
