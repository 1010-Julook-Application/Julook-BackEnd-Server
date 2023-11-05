package com.julook.domain.home.find.dto.response;

import com.julook.domain.home.find.dto.CommentInfoDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakLikesAndCommentsDTO {
    private int totalEvaluateCounts;
    private int likeCounts;
    private int dislikeCounts;
    private List<CommentInfoDTO> comments;
}
