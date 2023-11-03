package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishRequestDTO {
    private Long userId;
    private int makNumber;
}
