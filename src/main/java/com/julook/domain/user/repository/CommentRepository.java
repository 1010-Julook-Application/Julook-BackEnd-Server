package com.julook.domain.user.repository;

import com.julook.domain.user.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom{
}
