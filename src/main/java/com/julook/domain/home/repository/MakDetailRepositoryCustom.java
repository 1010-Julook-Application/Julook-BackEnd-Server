package com.julook.domain.home.repository;

import com.julook.domain.home.dto.MakInfoWithUserActionDTO;
import com.julook.domain.home.dto.response.MakLikesAndCommentsDTO;

public interface MakDetailRepositoryCustom {
    MakInfoWithUserActionDTO getMakDetailInfo(int makNumber, long userId);

    // 해당 막걸리 평가 및 코멘트 가져오기
    MakLikesAndCommentsDTO getMakLikesAndComments(int makNumber);
}
