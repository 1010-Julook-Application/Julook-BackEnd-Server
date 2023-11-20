package com.julook.domain.common.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakResponseDTO {
    private long makNumber;
    private String makName;
    private String makType;
    private String makImageNumber;
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MainDetail {
        private double makAlcoholPercentage;
        private int makVolume;
        private double makPrice;
    }
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Taste {
        private Double makTasteSweet;
        private Double makTasteSour;
        private Double makTasteThick;
        private Double makTasteFresh;
    }

    private MainDetail mainDetail;
    private Taste taste;
}
