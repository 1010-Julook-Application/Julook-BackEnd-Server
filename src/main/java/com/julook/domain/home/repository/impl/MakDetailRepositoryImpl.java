package com.julook.domain.home.repository.impl;

import com.julook.domain.common.entity.QMakInfo;
import com.julook.domain.home.dto.CommentInfoDTO;
import com.julook.domain.home.dto.EvaluateInfoDTO;
import com.julook.domain.home.dto.MakInUserActionDTO;
import com.julook.domain.home.dto.MakInfoWithUserActionDTO;
import com.julook.domain.home.dto.response.MakLikesAndCommentsDTO;
import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.home.repository.MakDetailRepositoryCustom;
import com.julook.domain.user.entity.QComment;
import com.julook.domain.user.entity.QEvaluateMak;
import com.julook.domain.user.entity.QUser;
import com.julook.domain.user.entity.QWishList;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class MakDetailRepositoryImpl implements MakDetailRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QMakInfo qMakInfo = QMakInfo.makInfo;
    private QWishList w = QWishList.wishList;

    private QUser u = QUser.user;
    private QEvaluateMak e = QEvaluateMak.evaluateMak;
    private QComment c = QComment.comment;

    @Autowired
    public MakDetailRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MakInfoWithUserActionDTO getMakDetailInfo(int makNumber, long userId) {
        // 쿼리 실행
        MakInfo makInfoResults = jpaQueryFactory
                .selectFrom(qMakInfo)
                .where(qMakInfo.makSeq.eq((long)makNumber))
                .fetchOne();

        MakInUserActionDTO userActionResults = jpaQueryFactory
                .select(
                        Projections.constructor(
                                MakInUserActionDTO.class,
                                Expressions.cases()
                                        .when(w.wishUserId.isNotNull().and(w.isUserDeleteWishMak.isNull())).then("Y")
                                        .otherwise("N"),
                                Expressions.cases()
                                        .when(e.evaluateId.isNotNull()).then("Y")
                                        .otherwise("N"),
                                Expressions.cases()
                                        .when(e.userLikedMak.eq('Y')).then("LIKE")
                                        .when(e.userLikedMak.eq('N')).then("DISLIKE")
                                        .otherwise("null"),
                                Expressions.cases()
                                        .when(c.commentId.isNotNull().and(c.isUserDeleted.isNull())).then("Y")
                                        .otherwise("N"),
                                c.commentId,
                                c.contents,
                                c.isVisible,
                                Expressions.cases()
                                        .when(e.updateDate.isNotNull()).then(e.updateDate)
                                        .otherwise(e.createDate)
                                        .as("writeDate")
                        )
                )
                .from(qMakInfo)
                .leftJoin(w).on(qMakInfo.makSeq.eq(w.wishMakId.longValue()).and(w.wishUserId.eq(userId)))
                .leftJoin(e).on(qMakInfo.makSeq.eq(e.evaluateMakId.longValue()).and(e.evaluateUserId.eq(userId)))
                .leftJoin(c).on(qMakInfo.makSeq.eq(c.commentMakId.longValue()).and(c.commentUserId.eq(userId)))
                .where(qMakInfo.makSeq.eq((long) makNumber))
                .fetchOne();

        MakInfoWithUserActionDTO result = MakInfoWithUserActionDTO.builder()
                .makInfo(makInfoResults)
                .userAction(userActionResults)
                .build();

        return result;
    }

//    @Override
//    public MakLikesAndCommentsDTO getMakLikesAndComments(int makNumber) {
//        // 쿼리 실행
//        Tuple eResults = jpaQueryFactory
//                .select(
//                        e.count(),
//                        e.userLikedMak.when('Y').then(1).otherwise(0).sum(),
//                        e.userLikedMak.when('N').then(1).otherwise(0).sum()
//                )
//                .from(e)
//                .where(e.evaluateMakId.eq((long) makNumber))
//                .fetchOne();
//
//
//        EvaluateInfoDTO evaluateResults = EvaluateInfoDTO.builder()
//                .totalEvaluateCounts(Math.toIntExact(eResults.get(0, Long.class)))
//                .likeCounts(Math.toIntExact(eResults.get(1, Integer.class)))
//                .dislikeCounts(Math.toIntExact(eResults.get(2, Integer.class)))
//                .build();
//
//
//        List<CommentInfoDTO> commentInfo = jpaQueryFactory
//                .select(
//                        Projections.bean(
//                                CommentInfoDTO.class,
//                                u.userNickName,// 사용자 닉네임
//                                e.userLikedMak.as("userLikeOrNot"), // 좋아요/싫어요 여부
//                                c.contents, // 댓글 내용
//                                Expressions.cases()
//                                        .when(e.updateDate.isNotNull()).then(e.updateDate)
//                                        .otherwise(e.createDate)
//                                        .as("writeDate")// 댓글 날짜
//                        )
//                )
//                .from(qMakInfo)
//                .leftJoin(e).on(qMakInfo.makSeq.eq(e.evaluateMakId))
//                .leftJoin(c).on(qMakInfo.makSeq.eq(c.commentMakId))
//                .leftJoin(u).on(e.evaluateUserId.eq(u.userID))
//                .where(
//                        qMakInfo.makSeq.eq((long) makNumber), // 막걸리 ID
//                        c.isUserDeleted.isNull(), // 댓글이 삭제되지 않은 경우
//                        c.isVisible.eq('Y') // 댓글이 보이는 경우
//                )
//                .fetch();
//
//
//        MakLikesAndCommentsDTO results = MakLikesAndCommentsDTO.builder()
//                .makEvaluateInfo(evaluateResults)
//                .comments(commentInfo)
//                .build();
//
//        return results;
//
//    }

    @Override
    public Page<MakLikesAndCommentsDTO> getMakLikesAndComments(int makNumber, Pageable pageable) {
        // 댓글 정보를 페이징하여 조회
        JPQLQuery<CommentInfoDTO> commentQuery = jpaQueryFactory
                .select(Projections.bean(
                        CommentInfoDTO.class,
                        u.userNickName,
                        e.userLikedMak.as("userLikeOrNot"),
                        c.contents,
                        Expressions.cases()
                                .when(e.updateDate.isNotNull()).then(e.updateDate)
                                .otherwise(e.createDate).as("writeDate")
                ))
                .from(qMakInfo)
                .leftJoin(e).on(qMakInfo.makSeq.eq(e.evaluateMakId))
                .leftJoin(c).on(qMakInfo.makSeq.eq(c.commentMakId))
                .leftJoin(u).on(e.evaluateUserId.eq(u.userID))
                .where(
                        qMakInfo.makSeq.eq((long) makNumber),
                        c.isUserDeleted.isNull(),
                        c.isVisible.eq('Y')
                );

        List<CommentInfoDTO> commentInfo = getCommentsPage(commentQuery, pageable).getContent();

        // 평가 정보 조회
        Tuple eResults = jpaQueryFactory
                .select(
                        e.count(),
                        e.userLikedMak.when('Y').then(1).otherwise(0).sum(),
                        e.userLikedMak.when('N').then(1).otherwise(0).sum()
                )
                .from(e)
                .where(e.evaluateMakId.eq((long) makNumber))
                .fetchOne();

        EvaluateInfoDTO evaluateResults = EvaluateInfoDTO.builder()
                .totalEvaluateCounts(Math.toIntExact(eResults.get(0, Long.class)))
                .likeCounts(Math.toIntExact(eResults.get(1, Integer.class)))
                .dislikeCounts(Math.toIntExact(eResults.get(2, Integer.class)))
                .build();

        return new PageImpl<>(Collections.singletonList(MakLikesAndCommentsDTO.builder()
                .makEvaluateInfo(evaluateResults)
                .comments(commentInfo)
                .build()), pageable, 1);
    }

    private Page<CommentInfoDTO> getCommentsPage(JPQLQuery<CommentInfoDTO> query, Pageable pageable) {
        List<CommentInfoDTO> results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(results, pageable, results.size());
    }

}
