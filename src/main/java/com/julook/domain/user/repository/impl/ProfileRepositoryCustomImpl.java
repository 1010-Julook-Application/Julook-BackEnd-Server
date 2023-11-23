package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.LinkAccountRequestDTO;
import com.julook.domain.user.dto.request.ModifyNickRequestDTO;
import com.julook.domain.user.entity.EvaluateMak;
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

    @Transactional
    @Override
    public User findExistsAccount(LinkAccountRequestDTO userRequest) {
        // '-'로 구분된 부분들을 추출
        String[] phoneParts = userRequest.getPhone().split("-");

        // 추출된 부분들을 각각의 변수에 저장
        String phonePrx = phoneParts[0];
        String phoneMid = phoneParts[1];
        String phoneSfx = phoneParts[2];

        String userBirth = userRequest.getBirth();;

        User userAcount = jpaQueryFactory
                .selectFrom(qUser)
                .where(qUser.userPhoneMiddle.eq(phoneMid).and(qUser.userPhoneSuffix.eq(phoneSfx)
                        .and(qUser.userBirth.eq(userBirth)).and(qUser.isUserWithdrawal.eq(false))))
                .fetchOne();

        return userAcount;
    }

    @Transactional
    @Override
    public Boolean linkAccount(User userAccount, LinkAccountRequestDTO userRequest) {
        if (userAccount  == null) {
            String[] phoneParts = userRequest.getPhone().split("-");
            String birth = userRequest.getBirth();

            // 추출된 부분들을 각각의 변수에 저장
            String phonePrx = phoneParts[0];
            String phoneMid = phoneParts[1];
            String phoneSfx = phoneParts[2];

            long affectedRows = jpaQueryFactory
                    .update(qUser)
                    .set(qUser.userPhonePrefix, phonePrx)
                    .set(qUser.userPhoneMiddle, phoneMid)
                    .set(qUser.userPhoneSuffix, phoneSfx)
                    .set(qUser.userBirth, birth)
                    .set(qUser.isUserVerified, "true")
                    .where(qUser.userID.eq(userRequest.getUserId()))
                    .execute();

            return affectedRows > 0;
        }

        return true;
    }
}
