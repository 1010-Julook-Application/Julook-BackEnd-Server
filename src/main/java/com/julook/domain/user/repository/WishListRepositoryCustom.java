package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.entity.WishList;

public interface WishListRepositoryCustom {
    // 찜 추가
    Boolean addWishList(WishRequestDTO userRequest);

    // 찜 삭제
    Boolean deleteWishList(WishRequestDTO userRequest);

    // 찜 있는지 확인
    WishList isAlreadyInWishList(Long userId, Long makNumber);

}
