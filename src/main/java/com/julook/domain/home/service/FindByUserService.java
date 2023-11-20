package com.julook.domain.home.service;

import java.util.List;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.home.dto.response.FindByUserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindByUserService {

    // 사용자 선택에 따라 막걸리 List 조회하는 함수
    Page<MakInfoDTO> findMakWithPaginationAndSorting(int offset, int pageSize, String sort, List<String> categories);
//    FindByUserResponseDTO getMakInfoListByUserPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable);

//    FindByUserResponseDTO findAll();
}
