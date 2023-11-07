package com.julook.domain.user.repository;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.user.entity.UserMakFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserMakFolderRepository extends JpaRepository<UserMakFolder, Long>, JpaSpecificationExecutor<UserMakFolder> {
}
