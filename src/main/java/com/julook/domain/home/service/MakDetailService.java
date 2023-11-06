package com.julook.domain.home.service;

import com.julook.domain.home.dto.response.MakDetailResponseDTO;
import com.julook.domain.home.dto.response.MakLikesAndCommentsDTO;

public interface MakDetailService {
    MakDetailResponseDTO getMakDetailInfo(int makNumber, long userId);

    // 막걸리 평가 및 코멘트 정보
    MakLikesAndCommentsDTO getMakLikesAndComments(int makNumber);
}
