package com.julook.domain.user.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyNickRequestDTO {
    Long userId;
    String modifyNickname;
}
