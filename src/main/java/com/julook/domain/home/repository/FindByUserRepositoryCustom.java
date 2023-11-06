package com.julook.domain.home.repository;

import com.julook.domain.common.entity.MakInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindByUserRepositoryCustom {
    List<MakInfo> findByPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable);
}
