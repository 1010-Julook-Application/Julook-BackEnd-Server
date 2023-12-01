package com.julook.domain.home.repository;

import com.julook.domain.common.entity.MakInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicMakRepository extends JpaRepository<MakInfo, Long> {
    @Query(value = "SELECT * FROM view_pohang_mak OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<MakInfo> findAllFromPohangView(int offset, int pageSize);

    @Query(value = "SELECT * FROM view_2023awards_mak OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<MakInfo> findAllFrom2023AwardsView(int offset, int pageSize);
}
