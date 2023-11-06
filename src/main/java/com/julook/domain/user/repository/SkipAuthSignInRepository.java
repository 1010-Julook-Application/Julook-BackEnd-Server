package com.julook.domain.user.repository;

import com.julook.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkipAuthSignInRepository extends JpaRepository<User, Long>, SkipAuthSignInRepositoryCustom{
}
