package com.julook.domain.fix.dto;

import com.julook.domain.home.dto.MakInfoDTO;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByDTO {
    private int recordCount;
    private Page<MakInfoDTO> makInfoDTO;
}
