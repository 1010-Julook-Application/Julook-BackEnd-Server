package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.EvaluateMakRequestDTO;
import com.julook.domain.user.entity.EvaluateMak;
import com.julook.domain.user.entity.QEvaluateMak;
import com.julook.domain.user.repository.EvaluateMakRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class EvaluateMakRepositoryImpl implements EvaluateMakRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private
    QEvaluateMak qEvaluateMak = QEvaluateMak.evaluateMak;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @Autowired
    public EvaluateMakRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    // 막걸리 평가를 안 한 경우 - insert
    @Transactional
    @Override
    public Boolean insertEvaluateMak(EvaluateMakRequestDTO userRequest) {
        long affectedRows = jpaQueryFactory.insert(qEvaluateMak)
                .columns(
                        qEvaluateMak.evaluateUserId, qEvaluateMak.evaluateMakId,
                        qEvaluateMak.userLikedMak
                ).values(
                        userRequest.getUserId(), userRequest.getMakNumber(),
                        userRequest.getLikeMak()
                )
                .execute();

        return affectedRows > 0;
    }

    // 막걸리 평가를 한 경우 - update
    @Transactional
    @Override
    public Boolean updateEvaluateMak(EvaluateMakRequestDTO userRequest) {
        System.out.println(userRequest.getLikeMak());
        long affectedRows = jpaQueryFactory
                .update(qEvaluateMak)
                .set(qEvaluateMak.userLikedMak, userRequest.getLikeMak())
                .set(qEvaluateMak.updateDate, currentDateTime)
                .where(
                        qEvaluateMak.evaluateUserId.eq(userRequest.getUserId())
                                .and(qEvaluateMak.evaluateMakId.eq(userRequest.getMakNumber())))
                .execute();

        return affectedRows > 0;
    }

    // 막걸리 평가 여부 확인
    @Override
    public Boolean isUserAlreadyEvaluateMak(EvaluateMakRequestDTO userRequest) {
        EvaluateMak affectedRows = jpaQueryFactory
                .selectFrom(qEvaluateMak)
                .where(qEvaluateMak.evaluateUserId.eq(userRequest.getUserId()).and(
                        qEvaluateMak.evaluateMakId.eq(userRequest.getMakNumber())))
                .fetchOne();

        return affectedRows != null;
    }

}
