package com.julook.domain.home.dto.response;

import com.julook.domain.home.dto.CommentInfoDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentCommentsResponseDTO {
    private Long makNumber;
    private String makName;
    private String makImageNumber;
    private CommentInfoDTO commentInfo;
}
