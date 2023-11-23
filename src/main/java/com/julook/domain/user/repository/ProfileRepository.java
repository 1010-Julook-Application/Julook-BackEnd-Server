package com.julook.domain.user.repository;

import com.julook.domain.user.entity.EvaluateMak;
import com.julook.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<User, Long>, ProfileRepositoryCustom{
}
