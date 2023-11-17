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
        LocalDateTime currentDateTime = LocalDateTime.now();
        long affectedRows;
        // 만약 likeMak 값이 null이 아니라면, 평가 업데이트 수행
        if (userRequest.getLikeMak() == 'Y' || userRequest.getLikeMak() == 'N') {
            affectedRows = jpaQueryFactory
                    .update(qEvaluateMak)
                    .set(qEvaluateMak.userLikedMak, userRequest.getLikeMak())
                    .set(qEvaluateMak.updateDate, currentDateTime)
                    .where(
                            qEvaluateMak.evaluateUserId.eq(userRequest.getUserId())
                                    .and(qEvaluateMak.evaluateMakId.eq((long) userRequest.getMakNumber())))
                    .execute();
            return affectedRows > 0;
        } else if (userRequest.getLikeMak() == 'D'){
            // likeMak 값이 null인 경우, 평가 초기화
            affectedRows = jpaQueryFactory
                    .delete(qEvaluateMak)
                    .where(
                            qEvaluateMak.evaluateUserId.eq(userRequest.getUserId())
                                    .and(qEvaluateMak.evaluateMakId.eq((long) userRequest.getMakNumber()))
                    )
                    .execute();
            return affectedRows > 0;
        }
        else {
            return false;
        }
    }

    // 막걸리 평가 여부 확인
    @Override
    public Boolean isUserAlreadyEvaluateMak(EvaluateMakRequestDTO userRequest) {
        EvaluateMak affectedRows = jpaQueryFactory
                .selectFrom(qEvaluateMak)
                .where(qEvaluateMak.evaluateUserId.eq(userRequest.getUserId()).and(
                        qEvaluateMak.evaluateMakId.eq((long) userRequest.getMakNumber())))
                .fetchOne();

        return affectedRows != null;
    }

}
