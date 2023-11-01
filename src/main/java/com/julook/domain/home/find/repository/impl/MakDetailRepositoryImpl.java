package com.julook.domain.home.find.repository.impl;

import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.home.find.entity.QMakInfo;
import com.julook.domain.home.find.repository.MakDetailRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MakDetailRepositoryImpl implements MakDetailRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private QMakInfo qMakInfo = QMakInfo.makInfo;

    @Autowired
    public MakDetailRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public MakInfo getMakDetailInfo(int makNumber) {
        // 쿼리 실행
        MakInfo results = jpaQueryFactory
                .selectFrom(qMakInfo)
                .where(qMakInfo.makSeq.eq((long)makNumber))
                .fetchOne();

        return results;
    }
}
