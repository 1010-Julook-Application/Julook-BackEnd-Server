package com.julook.domain.user.service.impl;

import com.julook.domain.common.dto.PageableInfoDTO;
import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.dto.MakInfoDTO;
import com.julook.domain.user.dto.MakCellInfoDTO;
import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.*;
import com.julook.domain.user.entity.Comment;
import com.julook.domain.user.entity.UserMakFolder;
import com.julook.domain.user.repository.CommentRepository;
import com.julook.domain.user.repository.EvaluateMakRepository;
import com.julook.domain.user.repository.UserMakFolderRepository;
import com.julook.domain.user.repository.WishListRepository;
import com.julook.domain.user.service.UserActionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserActionServiceImpl implements UserActionService {
    private final WishListRepository wishListRepository;
    private final EvaluateMakRepository evaluateMakRepository;
    private final CommentRepository commentRepository;
    private final UserMakFolderRepository userMakFolderRepository;
    private final ModelMapper modelMapper;

    public UserActionServiceImpl(WishListRepository wishListRepository, EvaluateMakRepository evaluateMakRepository, CommentRepository commentRepository, UserMakFolderRepository userMakFolderRepository, ModelMapper modelMapper) {
        this.wishListRepository = wishListRepository;
        this.evaluateMakRepository = evaluateMakRepository;
        this.commentRepository = commentRepository;
        this.userMakFolderRepository = userMakFolderRepository;
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
            responseDTO.setMessage("DB UPDATE 오류 발생");
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
            commentResponse.setMessage("DB INSERT 오류 발생");
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
            responseDTO.setMessage("DB UPDATE 오류 발생");
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
            responseDTO.setMessage("DB DELETE 오류 발생");
            return null;
        }
        return responseDTO;
    }

    @Override
    public Page<MakUserTableDTO> getUserMakFolder(Long userId, String segmentName, int offset, int pageSize) {
        Specification<UserMakFolder> specifications = Specification.where((root, query, builder) ->
                builder.equal(root.get("usrId"), userId));
        Specification<UserMakFolder> categorySpecification = null;

        switch (segmentName) {
            case "like":
                categorySpecification = (root, query, builder) ->
                        builder.equal(root.get("reactionLike"), "LIKE");
                break;
            case "dislike":
                categorySpecification = (root, query, builder) ->
                        builder.equal(root.get("reactionLike"), "DISLIKE");
                break;
            case "wish":
                categorySpecification = (root, query, builder) ->
                        builder.equal(root.get("reactionWish"), "WISH");
                break;
            case "comment":
                categorySpecification = (root, query, builder) ->
                        builder.isNotNull(root.get("reactionComment"));
                break;
            default:
                // 다 선택 안한 경우 조건을 걸지 않음
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
                PageRequest.of(offset, pageSize, Sort.by(Sort.Direction.DESC, "reactionCommentDate")));

        Page<MakUserTableDTO> MakUserTableList = result.map(entity -> modelMapper.map(entity, MakUserTableDTO.class));

        return MakUserTableList;
    }


//    @Override
//    public UserMakFolderResponseDTO getUserMakFolder(Long userId, String segmentName, int lastMakNum, Pageable pageable) {
//        Slice<UserMakFolder> folderResults = userMakFolderRepository.getUserMakFolder(userId, segmentName, lastMakNum, pageable);
//        int nextCursor = folderResults.isEmpty() ? 0 : folderResults.getContent()
//                .get(folderResults.getNumberOfElements() - 1).getMakSeq();
//
//        List<MakCellInfoDTO> makCellResults = new ArrayList<>();
//        for (UserMakFolder userMakFolder : folderResults.getContent()) {
//            System.out.println("Mak Number: " + userMakFolder.getMakSeq());
//            System.out.println("Mak Name: " + userMakFolder.getMakNm());
//            System.out.println("Mak Image: " + userMakFolder.getMakImg());
//            MakCellInfoDTO makCellInfoDTO = new MakCellInfoDTO();
//            makCellInfoDTO.setMakNumber(userMakFolder.getMakSeq());
//            makCellInfoDTO.setMakName(userMakFolder.getMakNm());
//            makCellInfoDTO.setMakImage(userMakFolder.getMakImg());
//            makCellResults.add(makCellInfoDTO);
//        }
//
//        PageableInfoDTO pageInfo = new PageableInfoDTO();
//        pageInfo.setCurrentPage(pageInfo.getCurrentPage());
//        pageInfo.setSize(pageInfo.getSize());
//        pageInfo.setFirst(pageInfo.isFirst());
//        pageInfo.setLast(!folderResults.hasNext());
//        pageInfo.setTotalMakElements(pageInfo.getTotalMakElements()); // 전체 막걸리 엘리먼트 수 설정
//        pageInfo.setTotalPages(pageInfo.getTotalPages()); // 전체 페이지 수 설정
//
//
//        UserMakFolderResponseDTO responseDTO = UserMakFolderResponseDTO.builder()
//                .userId(userId.toString())
//                .totalCounts(0)
//                .nextCursor(0)
//                .makInfo(makCellResults)
//                .pageInfo(pageInfo)
//                .build();
//
//        return responseDTO;
//
//    }


}
