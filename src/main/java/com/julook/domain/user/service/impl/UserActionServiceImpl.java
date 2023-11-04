package com.julook.domain.user.service.impl;

import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.CommentResponseDTO;
import com.julook.domain.user.dto.response.UserActionResponseDTO;
import com.julook.domain.user.entity.Comment;
import com.julook.domain.user.repository.CommentRepository;
import com.julook.domain.user.repository.EvaluateMakRepository;
import com.julook.domain.user.repository.WishListRepository;
import com.julook.domain.user.service.UserActionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserActionServiceImpl implements UserActionService {
    private final WishListRepository wishListRepository;
    private final EvaluateMakRepository evaluateMakRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public UserActionServiceImpl(WishListRepository wishListRepository, EvaluateMakRepository evaluateMakRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
        this.wishListRepository = wishListRepository;
        this.evaluateMakRepository = evaluateMakRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserActionResponseDTO addWishList(WishRequestDTO userRequest) {
        UserActionResponseDTO responseDTO = new UserActionResponseDTO();
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
    public UserActionResponseDTO deleteWishList(WishRequestDTO userRequest) {
        UserActionResponseDTO responseDTO = new UserActionResponseDTO();
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
    public UserActionResponseDTO evaluateMak(EvaluateMakRequestDTO userRequest) {
        UserActionResponseDTO responseDTO = new UserActionResponseDTO();
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

    @Override
    public CommentResponseDTO insertUserComment(CommentRequestDTO userRequest) {
        CommentResponseDTO commentResponse = new CommentResponseDTO();
        Comment comment = new Comment();
        String message;
        boolean isSuccess;
        try {
            UUID commentId = commentRepository.insertUserComment(userRequest);

            if (commentId != null) {
                isSuccess = true;
                message = "댓글 달기 성공";
            } else {
                isSuccess = false;
                message = "댓글 달기 실패";
            }

            commentResponse.setCommentId(commentId);
            commentResponse.setIsSuccess(isSuccess);
            commentResponse.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            commentResponse.setIsSuccess(false);
            commentResponse.setMessage("DB INSERT 오류 발생");
            return null;
        }
        return  commentResponse;
    }


    @Override
    public UserActionResponseDTO updateUserComment(CommentRequestDTO userRequest) {
        UserActionResponseDTO responseDTO = new UserActionResponseDTO();
        String message;
        try {
            Boolean isSuccess = commentRepository.updateUserComment(userRequest);

            if (isSuccess) {
                message = "댓글 수정 성공";
            } else {
                message = "댓글 수정 실패 - 해당 댓글 없음";
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
    public UserActionResponseDTO deleteUserComment(CommentRequestDTO userRequest) {
        UserActionResponseDTO responseDTO = new UserActionResponseDTO();
        String message;
        try {
            Boolean isSuccess = commentRepository.deleteUserComment(userRequest);

            if (isSuccess) {
                message = "댓글 삭제 성공";
            } else {
                message = "댓글 삭제 실패 - 해당 댓글 없음";
            }

            responseDTO.setIsSuccess(isSuccess);
            responseDTO.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
            responseDTO.setMessage("DB DELETE 오류 발생");
            return null;
        }
        return  responseDTO;
    }
}
