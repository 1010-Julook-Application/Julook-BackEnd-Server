package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.PhoneSignInRequestDTO;
import com.julook.domain.user.entity.QUser;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.repository.SignInRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SignInRepositoryImpl implements SignInRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QUser qUser = QUser.user;

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

    @Override
    public int findByUserNickName(String userNickName) {
        List<User> nickNameList = jpaQueryFactory.selectFrom(qUser)
                .where(qUser.userNickName.eq(userNickName))
                .fetch();

        return nickNameList.size();
    }

    @Transactional
    @Override
    public Boolean setUserInfo(Long userId, String nickName, String gender, String ageGroup) {
        LocalDateTime currentDateTime = LocalDateTime.now();
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

    @Transactional
    @Override
    public Boolean setUserInfoWithPhone(Long userID, PhoneSignInRequestDTO userRequest) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // '-'로 구분된 부분들을 추출
        String phone = userRequest.getUserPhone();
        String formattedPhone = phone.replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1 $2 $3");

        String[] phoneParts = formattedPhone.split(" ");
        String phonePrx = phoneParts[0];
        String phoneMid = phoneParts[1];
        String phoneSfx = phoneParts[2];

        long affectedRows = jpaQueryFactory.insert(qUser)
                .columns(
                        qUser.userID, qUser.userNickName, qUser.userPhonePrefix, qUser.userPhoneMiddle, qUser.userPhoneSuffix,
                        qUser.userSex, qUser.userBirth, qUser.isUserVerified, qUser.userJoinDate, qUser.isUserWithdrawal
                ).values(
                        userID, userRequest.getUserNickName(), phonePrx, phoneMid, phoneSfx,
                        userRequest.getUserSex(), userRequest.getUserBirth(), true, currentDateTime, false
                )
                .execute();

        return affectedRows > 0;
    }

    @Override
    public Boolean findByUserIdWithPhoneAndBirth(String phone, String birth) {
        return null;
    }


}
