package com.julook.domain.user.repository;

import com.julook.domain.user.dto.request.WishRequestDTO;

public interface WishListRepositoryCustom {
    // 찜 추가
    Boolean addWishList(WishRequestDTO userRequest);

    // 찜 삭제
    Boolean deleteWishList(WishRequestDTO userRequest);
}
