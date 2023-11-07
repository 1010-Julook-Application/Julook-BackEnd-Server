package com.julook.domain.home.dto.response;

import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.common.dto.PageableInfoDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindByUserResponseDTO {
    private List<MakInfoDTO> contents;
    private long totalCount;
    private long nextCursor;
    private List<PageableInfoDTO> pageInfo;

    public static FindByUserResponseDTO res(List<MakInfoDTO> contents, int totalCount, int nextCursor, List<PageableInfoDTO> pageInfo) {
        return FindByUserResponseDTO.builder()
                .contents(contents)
                .totalCount(totalCount)
                .nextCursor(nextCursor)
                .pageInfo(pageInfo)
                .build();
    }
}
