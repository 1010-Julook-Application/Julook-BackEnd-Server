package com.julook.domain.home.dto.response;

import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.common.dto.PageableInfoDTO;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByUserResponseDTO {
    private long recordCounts;
    private Page<MakInfoDTO> makInfo;

//    public static FindByUserResponseDTO res(List<MakInfoDTO> contents, int totalCount, int nextCursor, List<PageableInfoDTO> pageInfo) {
//        return FindByUserResponseDTO.builder()
//                .contents(contents)
//                .totalCount(totalCount)
//                .nextCursor(nextCursor)
//                .pageInfo(pageInfo)
//                .build();
//    }
}
