package com.jeff.mud.domain.charactor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharactor is a Querydsl query type for Charactor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCharactor extends EntityPathBase<Charactor> {

    private static final long serialVersionUID = -196529898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCharactor charactor = new QCharactor("charactor");

    public final QCharactorBag charactorBag;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.jeff.mud.domain.room.domain.QRoom room;

    public final EnumPath<com.jeff.mud.state.CharactorState> state = createEnum("state", com.jeff.mud.state.CharactorState.class);

    public QCharactor(String variable) {
        this(Charactor.class, forVariable(variable), INITS);
    }

    public QCharactor(Path<? extends Charactor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCharactor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCharactor(PathMetadata metadata, PathInits inits) {
        this(Charactor.class, metadata, inits);
    }

    public QCharactor(Class<? extends Charactor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactorBag = inits.isInitialized("charactorBag") ? new QCharactorBag(forProperty("charactorBag"), inits.get("charactorBag")) : null;
        this.room = inits.isInitialized("room") ? new com.jeff.mud.domain.room.domain.QRoom(forProperty("room")) : null;
    }

}

