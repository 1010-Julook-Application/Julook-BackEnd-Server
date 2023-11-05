package com.julook.domain.user.repository;

import com.julook.domain.user.entity.UserMakFolder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMakFolderRepository extends JpaRepository<UserMakFolder, Long>, UserMakFolderRepositoryCustom{
}
