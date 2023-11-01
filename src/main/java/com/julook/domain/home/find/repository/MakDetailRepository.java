package com.julook.domain.home.find.repository;

import com.julook.domain.home.find.entity.MakInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakDetailRepository extends JpaRepository<MakInfo, Long>, MakDetailRepositoryCustom{
}