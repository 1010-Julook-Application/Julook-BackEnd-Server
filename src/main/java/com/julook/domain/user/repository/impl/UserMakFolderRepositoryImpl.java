package com.julook.domain.user.repository.impl;

import com.julook.domain.user.entity.QUserMakFolder;
import com.julook.domain.user.entity.UserMakFolder;
import com.julook.domain.user.repository.UserMakFolderRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserMakFolderRepositoryImpl implements UserMakFolderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserMakFolder qUserMakFolder = QUserMakFolder.userMakFolder;

    @Autowired
    public UserMakFolderRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Transactional
    @Override
    public Slice<UserMakFolder> getUserMakFolder(Long userId, String segmentName, int lastMakNum, Pageable pageable) {
        //마지막 막걸리 번호를 기준으로 페이지네이션 적용
//        BooleanExpression lastMakNumExpression = qUserMakFolder.makSeq.lt(lastMakNum);

        // 쿼리

        List<UserMakFolder> results = jpaQueryFactory.selectFrom(qUserMakFolder)
                .where(qUserMakFolder.usrId.eq(userId))
                .fetch();

        System.out.println(results.get(0).getMakNm());
        System.out.println(results.get(1).getMakNm());
        System.out.println(results.get(2).getMakNm());
        System.out.println(results.get(3).getMakNm());

        return checkLastPage(pageable, results);
    }

    private Slice<UserMakFolder> checkLastPage(Pageable pageable, List<UserMakFolder> results) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (results.size() > pageable.getPageSize()) {
            hasNext = true;
            results.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(results, pageable, hasNext);
    }

}
