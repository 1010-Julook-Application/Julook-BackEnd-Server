package com.julook.domain.user.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserResponseDTO {
    private Boolean isSuccess;
    private String message;
    private String userId;
    private LocalDate withdrawDate;
}
