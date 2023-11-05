package com.julook.domain.home.find.service;

import com.julook.domain.home.find.dto.response.MakDetailResponseDTO;
import com.julook.domain.home.find.dto.response.MakLikesAndCommentsDTO;

public interface MakDetailService {
    MakDetailResponseDTO getMakDetailInfo(int makNumber, long userId);

    // 막걸리 평가 및 코멘트 정보
    MakLikesAndCommentsDTO getMakLikesAndComments(int makNumber);
}
