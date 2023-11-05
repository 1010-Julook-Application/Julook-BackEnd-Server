package com.julook.domain.home.find.dto;

import com.julook.domain.home.find.entity.MakInfo;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakInfoWithUserActionDTO {
    private MakInfo makInfo;
    private MakInUserActionDTO userAction;
}
