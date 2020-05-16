package com.jeff.mud.domain.stat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStat is a Querydsl query type for Stat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStat extends EntityPathBase<Stat> {

    private static final long serialVersionUID = -648407274L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStat stat = new QStat("stat");

    public final com.jeff.mud.domain.charactor.domain.QCharactor charactor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.jeff.mud.domain.stat.constants.Stats> type = createEnum("type", com.jeff.mud.domain.stat.constants.Stats.class);

    public final NumberPath<Integer> value = createNumber("value", Integer.class);

    public QStat(String variable) {
        this(Stat.class, forVariable(variable), INITS);
    }

    public QStat(Path<? extends Stat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStat(PathMetadata metadata, PathInits inits) {
        this(Stat.class, metadata, inits);
    }

    public QStat(Class<? extends Stat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactor = inits.isInitialized("charactor") ? new com.jeff.mud.domain.charactor.domain.QCharactor(forProperty("charactor"), inits.get("charactor")) : null;
    }

}

