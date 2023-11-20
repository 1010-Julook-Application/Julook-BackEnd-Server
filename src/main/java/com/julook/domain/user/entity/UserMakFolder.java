package com.julook.domain.user.entity;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Immutable
@Data
@Table(name = "user_mak_folder")
public class UserMakFolder {
    private Long usrId;
    @Id
    private int makSeq;
    private String makNm;
    private String makImg;
    private String reactionLike;
    private String reactionWish;
    private String reactionComment;
    private Character cmVisibility;
    private LocalDateTime reactionLikeDate;
    private LocalDateTime reactionWishDate;
    private LocalDateTime reactionCommentDate;
}
