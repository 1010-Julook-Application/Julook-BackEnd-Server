package com.julook.domain.search.repository.impl;

import com.julook.domain.common.entity.MakInfo;
import com.julook.domain.common.entity.QMakInfo;
import com.julook.domain.search.repository.SearchRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QMakInfo qMakInfo = QMakInfo.makInfo;

    @Autowired
    public SearchRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<MakInfo> getSearchResults(String keyword) {
        // 쿼리 실행
        try {
            List<MakInfo> results = jpaQueryFactory
                    .selectFrom(qMakInfo)
                    .where(qMakInfo.makName.like("%" + keyword + "%"))
                    .fetch();
            return results;

        } catch (Exception ex) {
            // 오류 처리 및 로깅
            ex.printStackTrace();
            return null;
        }
    }
}
