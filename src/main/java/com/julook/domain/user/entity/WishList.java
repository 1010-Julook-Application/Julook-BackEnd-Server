package com.julook.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_wish_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wl_seq")
    private int wishListSeq;

    @Column(name = "wl_id", columnDefinition = "uuid")
    private UUID wishListId;

    @Column(name = "mak_id")
    private long wishMakId;

    @Column(name = "usr_id")
    private Long wishUserId;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "wl_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime wishDate;

    @Column(name = "wl_del_yn")
    private Character isUserDeleteWishMak;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "wl_del_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime wishDelDate;

}
