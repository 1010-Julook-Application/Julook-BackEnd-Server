package com.julook.domain.user.dto.response;

import com.julook.domain.common.dto.PageableInfoDTO;
import com.julook.domain.user.dto.MakCellInfoDTO;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMakFolderResponseDTO {
    private String userId;
    private List<MakCellInfoDTO> makInfo;
    private int totalCounts;
    private int nextCursor;
    private PageableInfoDTO pageInfo;
}
