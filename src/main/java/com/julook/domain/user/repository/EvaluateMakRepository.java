package com.julook.domain.user.repository;

import com.julook.domain.user.entity.EvaluateMak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluateMakRepository extends JpaRepository<EvaluateMak, Long>, EvaluateMakRepositoryCustom{
}
