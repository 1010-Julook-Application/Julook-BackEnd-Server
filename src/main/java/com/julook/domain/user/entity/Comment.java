package com.julook.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comment")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cm_seq")
    private int commentSeq;

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "cm_id", columnDefinition = "BINARY(16)")
    private UUID commentId;

    @Column(name = "usr_id")
    private Long commentUserId;

    @Column(name = "mak_id")
    private Long commentMakId;

    @Column(name = "cm_content")
    private String contents;

    @Column(name = "cm_visible_yn")
    private Character isVisible;

    @CreationTimestamp
    @Column(name = "cm_create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @CreatedDate
    @Column(name = "cm_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

    @Column(name = "cm_del_yn")
    private Character isUserDeleted;

    @CreatedDate
    @Column(name = "cm_delete_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime deleteDate;

}