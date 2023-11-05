package com.julook.domain.user.dto;

import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakCellInfoDTO {
    private int makNumber;
    private String makName;
    private String makImage;
}