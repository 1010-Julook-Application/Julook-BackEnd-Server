package com.julook.domain.home.find.dto.response;

import com.julook.domain.home.find.dto.MakInUserActionDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakDetailResponseDTO {
    private Long makSeq;
    private String makType;
    private String makName;
    private String makImageNumber;
    private MakInUserActionDTO userAction;
    private List<Attribute> attributes;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attribute {
        private List<MainDetail> mainDetail;
        private List<Taste> taste;
        private String makContent;
        private List<String> makAwards;
        private String makRaw;
        private List<BreweryInfo> breweryInfo;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MainDetail {
        private Double makAlcoholPercentage;
        private Integer makVolume;
        private Integer makPrice;
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

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BreweryInfo {
        private String makBrewery;
        private String makBreweryLink;
        private String makSalesLink;
    }
}
