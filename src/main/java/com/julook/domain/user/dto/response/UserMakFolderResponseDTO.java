package com.julook.domain.user.dto.response;

import com.julook.domain.common.dto.PageableInfoDTO;
import com.julook.domain.user.dto.MakCellInfoDTO;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMakFolderResponseDTO {
    private Long userId;
    private Page<MakUserTableDTO> makUserTable;
}
