package com.julook.domain.home.repository.impl;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.common.entity.QMakInfo;
import com.julook.domain.home.dto.CommentInfoDTO;
import com.julook.domain.home.dto.response.RecentCommentsResponseDTO;
import com.julook.domain.home.repository.MainInfoRepositoryCustom;
import com.julook.domain.user.entity.QComment;
import com.julook.domain.user.entity.QEvaluateMak;
import com.julook.domain.user.entity.QUser;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MainInfoRepositoryImpl implements MainInfoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QMakInfo qMakInfo = QMakInfo.makInfo;
    private QUser qUser = QUser.user;
    private QEvaluateMak qEvaluateMak = QEvaluateMak.evaluateMak;
    private QComment qComment = QComment.comment;

    @Autowired
    public MainInfoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<MakInfo> getNewMakList() {
        try {
            List<MakInfo> results = jpaQueryFactory
                    .selectFrom(qMakInfo)
                    .orderBy(qMakInfo.makSeq.desc())
                    .limit(30)   // 일단 12개만..
                    .fetch();
            return results;

        } catch (Exception ex) {
            // 오류 처리 및 로깅
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RecentCommentsResponseDTO> getRecentComments() {
        try {
            List<RecentCommentsResponseDTO> commentResults;

            commentResults = jpaQueryFactory
                    .select(
                            Projections.bean(
                                    RecentCommentsResponseDTO.class,
                                    qMakInfo.makSeq.as("makNumber"),
                                    qMakInfo.makName.as("makName"),
                                    qMakInfo.makImageNumber,
                                    Projections.bean(
                                            CommentInfoDTO.class,
                                            qUser.userNickName.as("userNickName"),
                                            qEvaluateMak.userLikedMak.as("userLikeOrNot"),
                                            qComment.contents.as("contents"),
                                            Expressions.cases()
                                                    .when(qComment.updateDate.isNotNull()).then(qComment.updateDate)
                                                    .otherwise(qComment.createDate)
                                                    .as("writeDate")
                                    ).as("commentInfo")
                            )
                    )
                    .from(qMakInfo)
                    .leftJoin(qComment).on(qMakInfo.makSeq.eq(qComment.commentMakId))
                    .leftJoin(qUser).on(qComment.commentUserId.eq(qUser.userID))
                    .leftJoin(qEvaluateMak).on(qMakInfo.makSeq.eq(qEvaluateMak.evaluateMakId).and(qComment.commentUserId.eq(qEvaluateMak.evaluateUserId)))
                    .where(qComment.isVisible.eq('Y'))
                    .orderBy(qComment.updateDate != null ? qComment.updateDate.desc() : qComment.createDate.desc()) // writeDate 내림차순 정렬
                    .limit(30)
                    .fetch();

            return commentResults;



        } catch (Exception ex) {
            // 오류 처리 및 로깅
            ex.printStackTrace();
            return null;
        }
    }
}
