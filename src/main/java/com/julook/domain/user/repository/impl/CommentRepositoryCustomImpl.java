package com.julook.domain.user.repository.impl;

import com.julook.domain.user.dto.request.CommentRequestDTO;
import com.julook.domain.user.entity.Comment;
import com.julook.domain.user.entity.QComment;
import com.julook.domain.user.repository.CommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @PersistenceContext
    private EntityManager entityManager;

    private QComment qComment = QComment.comment;
    LocalDateTime currentDateTime = LocalDateTime.now();

    @Autowired
    public CommentRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Transactional
    @Override
    public UUID insertUserComment(CommentRequestDTO userRequest) {
        try {
            Comment comment = new Comment();

            UUID commentId = UUID.randomUUID(); // 새 UUID 생성
            comment.setCommentId(commentId);
            comment.setCommentUserId(userRequest.getUserId());
            comment.setCommentMakId((long) userRequest.getMakNumber());
            comment.setContents(userRequest.getContents());
            comment.setIsVisible(userRequest.getIsVisible());

            entityManager.persist(comment);
            entityManager.flush();

            return commentId;

        } catch (Exception ex) {
            // 오류 처리 및 로깅
            ex.printStackTrace();
            return null;

//        long affectedRows = jpaQueryFactory.insert(qComment)
//                .columns(
//                        qComment.commentUserId, qComment.commentMakId,
//                        qComment.contents, qComment.isVisible
//                ).values(
//                        userRequest.getUserId(), userRequest.getMakNumber(),
//                        userRequest.getContents(), userRequest.getIsVisible()
//                )
//                .execute();
//
//        return affectedRows > 0;
        }
    }

    @Transactional
    @Override
    public Boolean updateUserComment(CommentRequestDTO userRequest) {
        long affectedRows = jpaQueryFactory
                .update(qComment)
                .set(qComment.contents, userRequest.getContents())
                .set(qComment.isVisible, userRequest.getIsVisible())
                .set(qComment.updateDate, currentDateTime)
                .where(qComment.commentId.eq(userRequest.getCommentId()))
                .execute();
        return affectedRows > 0;
    }

    @Transactional
    @Override
    public Boolean deleteUserComment(CommentRequestDTO userRequest) {
        long affectedRows = jpaQueryFactory
                .update(qComment)
                .set(qComment.isUserDeleted, userRequest.getIsUserDeleteComment())
                .set(qComment.deleteDate, currentDateTime)
                .where(qComment.commentId.eq(userRequest.getCommentId()))
                .execute();
        return affectedRows > 0;
    }
}
