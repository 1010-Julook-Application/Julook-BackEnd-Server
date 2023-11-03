package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.WishRequestDTO;
import com.julook.domain.user.dto.response.WishResponseDTO;
import com.julook.domain.user.entity.QWishList;
import com.julook.domain.user.repository.WishListRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.springframework.data.jpa.domain.Specification.where;

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
        long affectedRows = jpaQueryFactory.insert(qWishList)
                .columns(
                        qWishList.wishMakId, qWishList.wishUserId, qWishList.wishDate,
                        qWishList.isUserDeleteWishMak
                ).values(
                    userRequest.getMakNumber(), userRequest.getUserId(), currentDateTime, 'N'
                )
                .execute();
        return affectedRows > 0;
    }

    @Transactional
    @Override
    public Boolean deleteWishList(WishRequestDTO userRequest) {
        long affectedRows = jpaQueryFactory
                .update(qWishList)
                .set(qWishList.isUserDeleteWishMak, 'Y')
                .where(
                qWishList.wishUserId.eq(userRequest.getUserId())
                        .and(qWishList.wishMakId.eq(userRequest.getMakNumber())))
                .execute();

        return affectedRows > 0;
    }
}
