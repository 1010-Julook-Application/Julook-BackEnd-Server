package com.julook.domain.user.repository.impl;

import com.julook.domain.user.entity.QUser;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.repository.SkipAuthSignInRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class SkipAuthSignInRepositoryImpl implements SkipAuthSignInRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QUser qUser = QUser.user;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @Autowired
    public SkipAuthSignInRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public User findByUserID(Long userId) {
        return jpaQueryFactory.selectFrom(qUser)
                .where(qUser.userID.eq(userId))
                .fetchOne();
    }

    @Transactional
    @Override
    public Boolean setUserInfo(Long userId, String nickName, String gender, String ageGroup) {
        long affectedRows = jpaQueryFactory.insert(qUser)
                .columns(
                        qUser.userID, qUser.userNickName, qUser.userSex, qUser.userAgeRange,
                        qUser.isUserVerified, qUser.userJoinDate, qUser.isUserWithdrawal
                ).values(
                        userId, nickName, gender, ageGroup, false, currentDateTime, false
                        )
//                .set(qUser.userID, userId)
//                .set(qUser.userNickName, nickName)
//                .set(qUser.userSex, gender)
//                .set(qUser.userAgeRange, ageGroup)
                .execute();

        return affectedRows > 0;
    }


}
