package com.julook.domain.user.service.impl;

import com.julook.domain.home.repository.FindByUserRepository;
import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.*;
import com.julook.domain.user.entity.Comment;
import com.julook.domain.user.entity.UserMakFolder;
import com.julook.domain.user.repository.*;
import com.julook.domain.user.service.UserActionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserActionServiceImpl implements UserActionService {
    private final WishListRepository wishListRepository;
    private final EvaluateMakRepository evaluateMakRepository;
    private final CommentRepository commentRepository;
    private final UserMakFolderRepository userMakFolderRepository;
    private final UserMakFolderRepositoryCustom userMakFolderRepositoryCustom;
    private final ModelMapper modelMapper;

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
            responseDTO.setMessage("DB INSERT 오류 발생"+ex.getMessage());
            return null;
        }
        return responseDTO;
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
            responseDTO.setMessage("DB UPDATE 오류 발생"+ex.getMessage());
            return null;
        }
        return responseDTO;
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

                if (isSuccess & userRequest.getLikeMak() != 'D') {
                    message = "막걸리 평가 업데이트 성공";
                } else if (isSuccess & userRequest.getLikeMak() == 'D' ){
                    message = "막걸리 평가 삭제 성공";
                } else {
                    message = "막걸리 평가 업데이트 실패";
                }
            }
            responseDTO.setIsSuccess(isSuccess);
            responseDTO.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
            responseDTO.setIsSuccess(false);
            responseDTO.setMessage("평가 처리 중 오류 발생"+ex.getMessage());
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
            Boolean commentId = commentRepository.insertUserComment(userRequest);

            if (commentId != null) {
                isSuccess = true;
                message = "댓글 달기 성공";
            } else {
                isSuccess = false;
                message = "댓글 달기 실패";
            }
            commentResponse.setIsSuccess(isSuccess);
            commentResponse.setMessage(message);

        } catch (Exception ex) {
            ex.printStackTrace();
            commentResponse.setIsSuccess(false);
            commentResponse.setMessage("DB INSERT 오류 발생"+ex.getMessage());
            return null;
        }
        return commentResponse;
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
            responseDTO.setMessage("DB UPDATE 오류 발생"+ex.getMessage());
            return null;
        }
        return responseDTO;
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
            responseDTO.setMessage("DB DELETE 오류 발생"+ex.getMessage());
            return null;
        }
        return responseDTO;
    }

    @Override
    public Page<MakUserTableDTO> getUserMakFolder(Long userId, String segmentName, int offset, int pageSize) {
        Specification<UserMakFolder> specifications = Specification.where((root, query, builder) ->
                builder.equal(root.get("usrId"), userId));
        Specification<UserMakFolder> categorySpecification = null;
        List<Sort.Order> orders = new ArrayList<>();

        switch (segmentName) {
            case "like":
                categorySpecification = (root, query, builder) ->
                        builder.equal(root.get("reactionLike"), "LIKE");
                orders.add(Sort.Order.desc("reactionLikeDate"));
                break;
            case "dislike":
                categorySpecification = (root, query, builder) ->
                        builder.equal(root.get("reactionLike"), "DISLIKE");
                orders.add(Sort.Order.desc("reactionLikeDate"));
                break;
            case "wish":
                categorySpecification = (root, query, builder) ->
                        builder.equal(root.get("reactionWish"), "WISH");
                orders.add(Sort.Order.desc("reactionWishDate"));
                break;
            case "comment":
                categorySpecification = (root, query, builder) ->
                        builder.isNotNull(root.get("reactionComment"));
                orders.add(Sort.Order.desc("reactionCommentDate"));
                break;
            default:
                // 다 선택 안한 경우 조건을 걸지 않음
                orders.add(Sort.Order.desc("reactionLikeDate"));
                orders.add(Sort.Order.desc("reactionWishDate"));
                orders.add(Sort.Order.desc("reactionCommentDate"));
                break;
        }

        if (categorySpecification != null) {
            specifications = specifications.and(categorySpecification);
        }

        specifications = specifications.and((root, query, builder) ->
                builder.or(
                                builder.isNotNull(root.get("reactionLike")),
                                builder.isNotNull(root.get("reactionWish")),
                                builder.isNotNull(root.get("reactionComment"))
                        )
        );


        Page<UserMakFolder> result = userMakFolderRepository.findAll(specifications,
                PageRequest.of(offset, pageSize, Sort.by(orders)));

        Page<MakUserTableDTO> MakUserTableList = result.map(entity -> modelMapper.map(entity, MakUserTableDTO.class));

        return MakUserTableList;
    }

    @Override
    public Long getTotalMak() {
        return userMakFolderRepositoryCustom.getTotalMak();
    }
}
