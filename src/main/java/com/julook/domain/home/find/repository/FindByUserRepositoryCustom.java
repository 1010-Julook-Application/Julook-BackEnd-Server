package com.julook.domain.home.find.repository;

import com.julook.domain.home.find.entity.MakInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindByUserRepositoryCustom {
    List<MakInfo> findByPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable);
}
