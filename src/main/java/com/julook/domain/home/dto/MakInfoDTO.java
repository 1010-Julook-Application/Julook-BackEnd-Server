package com.julook.domain.home.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakInfoDTO {
    private Long makSeq;
    private String makName;
    private String makType;
    private Double makAlcoholPercentage;
    private Integer makVolume;
    private Integer makPrice;
    private Double makTasteSweet;
    private Double makTasteSour;
    private Double makTasteThick;
    private Double makTasteFresh;
    private String makImageNumber;
}
