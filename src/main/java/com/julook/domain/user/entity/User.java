package com.julook.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_seq")
    private Long userSeq;

    @Column(name = "usr_id")
    private Long userID;

    @Column(name = "usr_nick_nm")
    private String userNickName;

    @Column(name = "usr_phone_prx")
    private String userPhonePrefix;

    @Column(name = "usr_phone_mid")
    private String userPhoneMiddle;

    @Column(name = "usr_phone_sfx")
    private String userPhoneSuffix;

    @Column(name = "usr_birth")
    private String userBirth;

    @Column(name = "usr_sex")
    private String userSex;

    @Column(name = "usr_age")
    private String userAgeRange;

    @Column(name = "usr_verified_yn")
    private String isUserVerified;

    @Column(name = "usr_join_date")
    private LocalDate userJoinDate;

    @Column(name = "usr_wdr_yn")
    private Boolean isUserWithdrawal;

    @Column(name = "usr_wdr_date")
    private LocalDate userWithdrawalDate;

}
