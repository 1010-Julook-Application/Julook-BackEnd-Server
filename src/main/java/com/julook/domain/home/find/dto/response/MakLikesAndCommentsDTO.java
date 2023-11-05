package com.julook.domain.home.find.dto.response;

import com.julook.domain.common.dto.PageableInfoDTO;
import com.julook.domain.home.find.dto.CommentInfoDTO;
import com.julook.domain.home.find.dto.EvaluateInfoDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakLikesAndCommentsDTO {
    private EvaluateInfoDTO makEvaluateInfo;
    private List<CommentInfoDTO> comments;
    private PageableInfoDTO pageableInfo;
}
