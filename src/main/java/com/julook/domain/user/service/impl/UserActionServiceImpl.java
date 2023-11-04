package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.EvaluateMakResponseDTO;
import com.julook.domain.user.dto.response.WishResponseDTO;
import com.julook.domain.user.repository.EvaluateMakRepository;
import com.julook.domain.user.repository.WishListRepository;
import com.julook.domain.user.service.UserActionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserActionServiceImpl implements UserActionService {
    private final WishListRepository wishListRepository;
    private final EvaluateMakRepository evaluateMakRepository;
    private final ModelMapper modelMapper;

    public UserActionServiceImpl(WishListRepository wishListRepository, EvaluateMakRepository evaluateMakRepository, ModelMapper modelMapper) {
        this.wishListRepository = wishListRepository;
        this.evaluateMakRepository = evaluateMakRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WishResponseDTO addWishList(WishRequestDTO userRequest) {
        WishResponseDTO responseDTO = new WishResponseDTO();
        String message;
        try {
            Boolean isSuccess = wishListRepository.addWishList(userRequest);

            if (isSuccess) {
                message = "찜하기 성공";
            } else {
                message = "찜하기 실패";
            }

            responseDTO.setIsSuccess(isSuccess);
            responseDTO.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
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
            Boolean isSuccess = wishListRepository.deleteWishList(userRequest);

            if (isSuccess) {
                message = "찜하기 삭제 성공";
            } else {
                message = "찜하기 삭제 실패";
            }

            responseDTO.setIsSuccess(isSuccess);
            responseDTO.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
            responseDTO.setMessage("DB UPDATE 오류 발생");
            return null;
        }
        return  responseDTO;
    }

    @Override
    public EvaluateMakResponseDTO evaluateMak(EvaluateMakRequestDTO userRequest) {
        EvaluateMakResponseDTO responseDTO = new EvaluateMakResponseDTO();
        String message;
        Boolean isSuccess;
        try {
            if (!evaluateMakRepository.isUserAlreadyEvaluateMak(userRequest)) {
                isSuccess = evaluateMakRepository.insertEvaluateMak(userRequest);

                if (isSuccess) {
                    message = "막걸리 평가 성공";

                } else {
                    message = "막걸리 평가 실패";
                }
            } else {
                isSuccess = evaluateMakRepository.updateEvaluateMak(userRequest);

                if (isSuccess) {
                    message = "막걸리 평가 업데이트 성공";
                } else {
                    message = "막걸리 평가 업데이트 실패";
                }
            }
            responseDTO.setIsSuccess(isSuccess);
            responseDTO.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
            responseDTO.setMessage("평가 처리 중 오류 발생");
        }

        return responseDTO;

    }
}
