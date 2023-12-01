package com.julook.domain.user.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyNickResponseDTO {
    private Boolean isSuccess;
    private String message;
    private Long userId;
    private String modifiedNick;
}
