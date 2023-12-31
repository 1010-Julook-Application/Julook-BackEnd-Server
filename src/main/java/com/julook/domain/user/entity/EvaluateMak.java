package com.julook.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_mak_evaluate")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EvaluateMak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mev_seq")
    private int evaluateSeq;

    @Column(name = "mev_id", columnDefinition = "uuid")
    private UUID evaluateId;

    @Column(name = "usr_id")
    private Long evaluateUserId;

    @Column(name = "mak_id")
    private Long evaluateMakId;

    @Column(name = "mev_like_yn")
    private Character userLikedMak;

    @CreatedDate
    @Column(name = "mev_create_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "mev_update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updateDate;

}
