package com.julook.domain.home.find.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMakInfo is a Querydsl query type for MakInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMakInfo extends EntityPathBase<MakInfo> {

    private static final long serialVersionUID = -157418445L;

    public static final QMakInfo makInfo = new QMakInfo("makInfo");

    public final NumberPath<Double> makAlcoholPercentage = createNumber("makAlcoholPercentage", Double.class);

    public final StringPath makAwards = createString("makAwards");

    public final StringPath makBrewery = createString("makBrewery");

    public final StringPath makBreweryLink = createString("makBreweryLink");

    public final StringPath makContent = createString("makContent");

    public final ComparablePath<java.util.UUID> makId = createComparable("makId", java.util.UUID.class);

    public final StringPath makImageNumber = createString("makImageNumber");

    public final StringPath makName = createString("makName");

    public final NumberPath<Integer> makPrice = createNumber("makPrice", Integer.class);

    public final StringPath makRaw = createString("makRaw");

    public final StringPath makSalesLink = createString("makSalesLink");

    public final NumberPath<Long> makSeq = createNumber("makSeq", Long.class);

    public final NumberPath<Double> makTasteFresh = createNumber("makTasteFresh", Double.class);

    public final NumberPath<Double> makTasteSour = createNumber("makTasteSour", Double.class);

    public final NumberPath<Double> makTasteSweet = createNumber("makTasteSweet", Double.class);

    public final NumberPath<Double> makTasteThick = createNumber("makTasteThick", Double.class);

    public final StringPath makType = createString("makType");

    public final NumberPath<Integer> makVolume = createNumber("makVolume", Integer.class);

    public QMakInfo(String variable) {
        super(MakInfo.class, forVariable(variable));
    }

    public QMakInfo(Path<? extends MakInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMakInfo(PathMetadata metadata) {
        super(MakInfo.class, metadata);
    }

}

