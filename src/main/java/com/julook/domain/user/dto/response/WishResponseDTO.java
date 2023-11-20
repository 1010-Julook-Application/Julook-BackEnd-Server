package com.julook.domain.user.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishResponseDTO {
    private String isInUserWishList;
    private Boolean isSuccess;
    private String message;
}
