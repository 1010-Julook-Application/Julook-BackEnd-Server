package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.entity.QEvaluateMak;
import com.julook.domain.user.entity.QUser;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.repository.ProfileRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QUser qUser = QUser.user;

    @Transactional
    @Override
    public Boolean modifyUserNickname(ModifyNickRequestDTO userRequest) {
        long affectedRows = jpaQueryFactory
                .update(qUser)
                .set(qUser.userNickName, userRequest.getModifyNickname())
                .where(qUser.userID.eq(userRequest.getUserId()))
                .execute();

        return affectedRows > 0;
    }

    @Transactional
    @Override
    public Boolean deleteUserAccount(Long userId) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        long affectedRows = jpaQueryFactory
                .update(qUser)
                .set(qUser.isUserWithdrawal, true)
                .set(qUser.userWithdrawalDate, currentDateTime.toLocalDate())
                .where(qUser.userID.eq(userId))
                .execute();

        return affectedRows > 0;
    }
}
