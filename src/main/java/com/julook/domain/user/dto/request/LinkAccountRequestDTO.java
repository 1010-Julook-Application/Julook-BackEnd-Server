package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkAccountRequestDTO {
    private Long userId;
    private String phone;
    private String birth;
}
