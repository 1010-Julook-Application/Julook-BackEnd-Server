package com.julook.domain.user.repository;

import com.julook.domain.user.entity.UserMakFolder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserMakFolderRepositoryCustom {
    // 사용자 찜 폴더에서 선택에 따른 막걸리 조회
    Slice<UserMakFolder> getUserMakFolder(Long userId, String segmentName, int lastMakNum, Pageable pageable);

    // 전체 막걸리 개수 조회
    long getTotalMak();
}
