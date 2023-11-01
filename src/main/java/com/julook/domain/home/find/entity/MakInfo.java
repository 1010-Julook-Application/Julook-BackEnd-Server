package com.julook.domain.home.find.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_mak_Info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MakInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mak_seq")
    private Long makSeq;

    @Column(name = "mak_id", columnDefinition = "uuid")
    private UUID makId;

    @Column(name = "mak_nm")
    private String makName;

    @Column(name = "mak_type")
    private String makType;

    @Column(name = "mak_al_per")
    private Double makAlcoholPercentage;

    @Column(name = "mak_vol")
    private Integer makVolume;

    @Column(name = "mak_price")
    private Integer makPrice;

    @Column(name = "mak_tst_sweet")
    private Double makTasteSweet;

    @Column(name = "mak_tst_sour")
    private Double makTasteSour;

    @Column(name = "mak_tst_thick")
    private Double makTasteThick;

    @Column(name = "mak_tst_fresh")
    private Double makTasteFresh;

    @Column(name = "mak_content", columnDefinition = "TEXT")
    private String makContent;

    @Column(name = "mak_awards")
    private String makAwards;

    @Column(name = "mak_raw")
    private String makRaw;

    @Column(name = "mak_brewery")
    private String makBrewery;

    @Column(name = "mak_bre_link")
    private String makBreweryLink;

    @Column(name = "mak_sales_link")
    private String makSalesLink;

    @Column(name = "mak_img")
    private String makImageNumber;
}
