package com.julook.domain.home.repository;

import com.julook.domain.common.entity.MakInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakDetailRepository extends JpaRepository<MakInfo, Long>, MakDetailRepositoryCustom{
}
