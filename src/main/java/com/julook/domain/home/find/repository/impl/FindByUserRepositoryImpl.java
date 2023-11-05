package com.julook.domain.home.find.repository.impl;

import com.julook.domain.home.find.dto.MakInUserActionDTO;
import com.julook.domain.home.find.entity.MakInfo;
import com.julook.domain.home.find.entity.QMakInfo;
import com.julook.domain.home.find.repository.FindByUserRepositoryCustom;
import com.julook.domain.user.entity.QComment;
import com.julook.domain.user.entity.QEvaluateMak;
import com.julook.domain.user.entity.QWishList;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.core.types.Projections.tuple;

@Repository
public class FindByUserRepositoryImpl implements FindByUserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private QMakInfo qMakInfo = QMakInfo.makInfo;

    @Autowired
    public FindByUserRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<MakInfo> findByPreferences(Long lastMakNum, List<String> categories, String sort, Pageable pageable) {
        //마지막 막걸리 번호를 기준으로 페이지네이션 적용
        BooleanExpression lastMakNumExpression = qMakInfo.makSeq.lt(lastMakNum);

        // 사용자 선택 카테고리 필터링 적용
        BooleanExpression categoryExpression = buildCategoryExpression(categories);

        // 정렬 방식에 따른 정렬 쿼리를 생성
        com.querydsl.core.types.OrderSpecifier<?> orderByExpression = getOrderByExpression(sort);

        // 쿼리 실행

        List<MakInfo> results = jpaQueryFactory.selectFrom(qMakInfo)
                .where(lastMakNumExpression, categoryExpression)
                .orderBy(orderByExpression)
                .limit(pageable.getPageSize())
                .fetch();

        return results;
    }
//    private BooleanExpression ltLastMakNum(Long lastMakNum) {
//        if (lastMakNum == null) {
//            lastMakNum = 30000L;
//        }
//
//        return qMakInfo.makSeq.lt(lastMakNum);
//    }

    // 카테고리 필터링을 적용하는 메소드
    private BooleanExpression buildCategoryExpression(List<String> categories) {
        if (categories.isEmpty()) {
            // 아무 카테고리도 선택하지 않은 경우
            return null;
        }

        BooleanExpression categoryExpression = null;
        for (String category : categories) {
            BooleanExpression condition;
            switch (category) {
                case "sweet":
                    condition = qMakInfo.makTasteSweet.between(3, 5);
                    break;
                case "thick":
                    condition = qMakInfo.makTasteThick.between(3, 5);
                    break;
                case "noAspartame":
                    // 아스파탐없는 경우에는 조건을 걸지 않음
                    continue;
                case "sour":
                    condition = qMakInfo.makTasteSour.between(3, 5);
                    break;
                case "fresh":
                    condition = qMakInfo.makTasteFresh.between(3, 5);
                    break;
                default:
                    // 다 선택 안한경우 조건 걸지 않음
                    continue;
            }
            if (categoryExpression == null) {
                categoryExpression = condition;
            } else {
                categoryExpression = categoryExpression.and(condition);
            }
        }
        return categoryExpression;
    }

    // 정렬 방식에 따른 정렬 쿼리를 반환하는 메소드
    private com.querydsl.core.types.OrderSpecifier<?> getOrderByExpression(String sort) {
        if ("highAlcohol".equals(sort)) {
            return qMakInfo.makAlcoholPercentage.desc();
        } else if ("lowAlcohol".equals(sort)) {
            return qMakInfo.makAlcoholPercentage.asc();
        }
        return qMakInfo.makSeq.desc(); // 기본값은 추천순
    }

}
