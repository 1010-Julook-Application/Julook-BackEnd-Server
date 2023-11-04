package com.julook.domain.user.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserActionResponseDTO {
    private Boolean isSuccess;
    private String message;

}

