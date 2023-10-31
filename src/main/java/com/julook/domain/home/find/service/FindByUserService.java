package com.julook.domain.home.find.service;

import java.util.List;
import com.julook.domain.home.find.dto.MakInfoDTO;
import org.springframework.data.domain.Pageable;

public interface FindByUserService {

    // 사용자 선택에 따라 막걸리 List 조회하는 함수
    List<MakInfoDTO> getMakInfoListByUserPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable);
}
