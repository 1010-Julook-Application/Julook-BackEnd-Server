package com.julook.domain.home.find.repository.impl;

import com.julook.domain.home.find.dto.MakInUserActionDTO;
import com.julook.domain.home.find.dto.MakInfoWithUserActionDTO;
import com.julook.domain.home.find.dto.response.MakLikesAndCommentsDTO;
import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.home.find.entity.QMakInfo;
import com.julook.domain.home.find.repository.MakDetailRepositoryCustom;
import com.julook.domain.user.entity.QComment;
import com.julook.domain.user.entity.QEvaluateMak;
import com.julook.domain.user.entity.QWishList;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MakDetailRepositoryImpl implements MakDetailRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QMakInfo qMakInfo = QMakInfo.makInfo;
    private QWishList w = QWishList.wishList;
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
                                c.isVisible
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
}
