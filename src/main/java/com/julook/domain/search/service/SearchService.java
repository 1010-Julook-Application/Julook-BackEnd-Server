package com.julook.domain.search.service;

import com.julook.domain.common.dto.response.MakResponseDTO;

import java.util.List;

public interface SearchService {
    List<MakResponseDTO> getSearchResults(String keyword);
}
