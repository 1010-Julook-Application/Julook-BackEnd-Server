package com.julook.domain.home.service;

import com.julook.domain.common.dto.response.MakResponseDTO;
import com.julook.domain.home.dto.response.RecentCommentsResponseDTO;

import java.util.List;

public interface MainInfoService {
    List<MakResponseDTO> getNewMakList();

    // 최근 댓글
    List<RecentCommentsResponseDTO> getRecentComments();
}
