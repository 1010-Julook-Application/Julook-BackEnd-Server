package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.SkipSignInResponseDTO;
import com.julook.domain.user.dto.response.WishResponseDTO;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.entity.WishList;
import com.julook.domain.user.repository.WishListRepository;
import com.julook.domain.user.service.UserActionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserActionServiceImpl implements UserActionService {
    private final WishListRepository wishListRepository;
    private final ModelMapper modelMapper;

    public UserActionServiceImpl(WishListRepository wishListRepository, ModelMapper modelMapper) {
        this.wishListRepository = wishListRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WishResponseDTO addWishList(WishRequestDTO userRequest) {
        WishResponseDTO responseDTO = new WishResponseDTO();
        String message;
        try {
            Boolean isSuccessed = wishListRepository.addWishList(userRequest);

            if (isSuccessed) {
                message = "찜하기 성공";
            } else {
                message = "찜하기 실패";
            }

            responseDTO.setIsSuccessed(isSuccessed);
            responseDTO.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccessed(false);
            responseDTO.setMessage("DB INSERT 오류 발생");
            return null;
        }
        return  responseDTO;
    }

    @Override
    public WishResponseDTO deleteWishList(WishRequestDTO userRequest) {
        WishResponseDTO responseDTO = new WishResponseDTO();
        String message;
        try {
            Boolean isSuccessed = wishListRepository.deleteWishList(userRequest);

            if (isSuccessed) {
                message = "찜하기 삭제 성공";
            } else {
                message = "찜하기 삭제 실패";
            }

            responseDTO.setIsSuccessed(isSuccessed);
            responseDTO.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccessed(false);
            responseDTO.setMessage("DB UPDATE 오류 발생");
            return null;
        }
        return  responseDTO;
    }
}
