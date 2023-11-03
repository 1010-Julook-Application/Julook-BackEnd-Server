package com.julook.domain.user.service;

import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.WishResponseDTO;
import com.julook.domain.user.entity.WishList;

public interface UserActionService {
    // 찜 추가
    WishResponseDTO addWishList(WishRequestDTO userRequest);

    // 찜 삭제
    WishResponseDTO deleteWishList(WishRequestDTO userRequest);
}
