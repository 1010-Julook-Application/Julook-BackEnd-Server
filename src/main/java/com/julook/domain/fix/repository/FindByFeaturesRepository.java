package com.julook.domain.fix.repository;

import com.julook.domain.common.entity.MakInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FindByFeaturesRepository extends JpaRepository<MakInfo, Long> , JpaSpecificationExecutor<MakInfo> {
}
