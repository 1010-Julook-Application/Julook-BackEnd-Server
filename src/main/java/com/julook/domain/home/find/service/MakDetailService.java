package com.julook.domain.home.find.service;

import com.julook.domain.home.find.dto.response.MakDetailResponseDTO;

public interface MakDetailService {
    MakDetailResponseDTO getMakDetailInfo(int makNumber, long userId);
}
