package com.julook.domain.user.repository.impl;

import com.julook.domain.user.entity.QUser;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.repository.SignInRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class SignInRepositoryImpl implements SignInRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QUser qUser = QUser.user;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @Autowired
    public SignInRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
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
                .execute();

        return affectedRows > 0;
    }

    @Override
    public Boolean setUserInfoWithPhone(Long userId, String phone, String birth) {
        // '-'로 구분된 부분들을 추출
//        String[] phoneParts = phone.split("-");
//
//        // 추출된 부분들을 각각의 변수에 저장
//        String phonePrx = phoneParts[0];
//        String phoneMid = phoneParts[1];
//        String phoneSfx = phoneParts[2];
//
//        long affectedRows = jpaQueryFactory.insert(qUser)
//                .columns(
//                        qUser.userID, qUser.userNickName, qUser.userPhonePrefix, qUser.userPhoneMiddle, qUser.userPhoneSuffix,
//                        qUser.userSex, qUser.userBirth, qUser.isUserVerified, qUser.userJoinDate, qUser.isUserWithdrawal
//                ).values(
//                        userId, nickName, gender, ageGroup, false, currentDateTime, false
//                )
//                .execute();
//
//        return affectedRows > 0;
        return null;
    }

    @Override
    public Boolean findByUserIdWithPhoneAndBirth(String phone, String birth) {
        return null;
    }


}
