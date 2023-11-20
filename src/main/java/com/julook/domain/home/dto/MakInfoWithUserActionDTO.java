package com.julook.domain.home.dto;

import com.julook.domain.common.entity.MakInfo;
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
