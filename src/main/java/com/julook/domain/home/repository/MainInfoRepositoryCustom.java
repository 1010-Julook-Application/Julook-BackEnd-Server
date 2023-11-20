package com.julook.domain.home.repository;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.dto.response.RecentCommentsResponseDTO;

import java.util.List;

public interface MainInfoRepositoryCustom {
    // 최근 막걸리
    List<MakInfo> getNewMakList();

    // 최근 댓글
    List<RecentCommentsResponseDTO> getRecentComments();
}
