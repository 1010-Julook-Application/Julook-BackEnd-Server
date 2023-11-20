package com.julook.domain.search.repository;

import com.julook.domain.common.entity.MakInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<MakInfo, Long>, SearchRepositoryCustom{
}
