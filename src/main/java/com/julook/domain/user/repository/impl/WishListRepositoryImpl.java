package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.entity.QWishList;
import com.julook.domain.user.entity.User;
import com.julook.domain.user.entity.WishList;
import com.julook.domain.user.repository.WishListRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class WishListRepositoryImpl implements WishListRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QWishList qWishList = QWishList.wishList;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @Autowired
    public WishListRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Transactional
    @Override
    public Boolean addWishList(WishRequestDTO userRequest) {
        try {
            WishList results = isAlreadyInWishList(userRequest.getUserId(), (long) userRequest.getMakNumber());
            long affectedRows = 0;
            if (results != null) {
                affectedRows = jpaQueryFactory.update(qWishList)
                        .set(qWishList.isUserDeleteWishMak, 'N')
                        .set(qWishList.wishDelDate, (LocalDateTime) null)
                        .where(
                                qWishList.wishUserId.eq(userRequest.getUserId())
                                        .and(qWishList.wishMakId.eq((long) userRequest.getMakNumber())))
                        .execute();
            } else {
                affectedRows = jpaQueryFactory.insert(qWishList)
                        .columns(
                                qWishList.wishMakId, qWishList.wishUserId,
                                qWishList.isUserDeleteWishMak
                        ).values(
                                userRequest.getMakNumber(), userRequest.getUserId(), 'N'
                        )
                        .execute();
            }

            return affectedRows > 0;
        } catch (Exception ex) {
            // 오류 처리 및 로깅
            ex.printStackTrace();
            return null;

        }
    }

    @Transactional
    @Override
    public Boolean deleteWishList(WishRequestDTO userRequest) {
        long affectedRows = jpaQueryFactory
                .update(qWishList)
                .set(qWishList.isUserDeleteWishMak, 'Y')
                .set(qWishList.wishDelDate, currentDateTime)
                .where(
                        qWishList.wishUserId.eq(userRequest.getUserId())
                                .and(qWishList.wishMakId.eq((long) userRequest.getMakNumber())))
                .execute();

        return affectedRows > 0;
    }

    @Override
    public WishList isAlreadyInWishList(Long userId, Long makNumber) {
        WishList affectedRows = jpaQueryFactory.selectFrom(qWishList)
                .where(qWishList.wishUserId.eq(userId).and(qWishList.wishMakId.eq(makNumber)))
                .fetchOne();

        return affectedRows;
    }


}
