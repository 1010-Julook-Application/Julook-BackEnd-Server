package com.julook.domain.user.entity;

import com.querydsl.core.annotations.Immutable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Immutable
@Data
@Table(name = "user_mak_folder")
@NoArgsConstructor
@AllArgsConstructor
public class UserMakFolder {
    @Id
    private Long usrId;

    private int makSeq;
    private String makNm;
    private String makImg;
    private String reactionLike;
    private String reactionWish;
    private String reactionComment;
    private LocalDateTime reactionLikeDate;
    private LocalDateTime reactionWishDate;
    private LocalDateTime reactionCommentDate;

}
