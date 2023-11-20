package com.julook.domain.search.repository;

import com.julook.domain.common.entity.MakInfo;

import java.util.List;

public interface SearchRepositoryCustom {
    // 검색어로 조회
    List<MakInfo> getSearchResults(String keyword);
}
