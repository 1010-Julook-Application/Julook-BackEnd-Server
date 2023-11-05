package com.julook.domain.home.find.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluateInfoDTO {
    private int totalEvaluateCounts;
    private int likeCounts;
    private int dislikeCounts;
}
