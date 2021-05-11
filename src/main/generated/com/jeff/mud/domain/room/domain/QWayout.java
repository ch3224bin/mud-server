package com.jeff.mud.domain.room.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWayout is a Querydsl query type for Wayout
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWayout extends EntityPathBase<Wayout> {

    private static final long serialVersionUID = 2048537736L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWayout wayout = new QWayout("wayout");

    public final EnumPath<com.jeff.mud.domain.room.constants.Direction> direction = createEnum("direction", com.jeff.mud.domain.room.constants.Direction.class);

    public final QDoor door;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isShow = createBoolean("isShow");

    public final QRoom nextRoom;

    public final QRoom room;

    public QWayout(String variable) {
        this(Wayout.class, forVariable(variable), INITS);
    }

    public QWayout(Path<? extends Wayout> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWayout(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWayout(PathMetadata metadata, PathInits inits) {
        this(Wayout.class, metadata, inits);
    }

    public QWayout(Class<? extends Wayout> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.door = inits.isInitialized("door") ? new QDoor(forProperty("door")) : null;
        this.nextRoom = inits.isInitialized("nextRoom") ? new QRoom(forProperty("nextRoom")) : null;
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room")) : null;
    }

}

