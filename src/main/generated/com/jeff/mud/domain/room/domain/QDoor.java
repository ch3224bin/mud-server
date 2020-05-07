package com.jeff.mud.domain.room.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDoor is a Querydsl query type for Door
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDoor extends EntityPathBase<Door> {

    private static final long serialVersionUID = 184818807L;

    public static final QDoor door = new QDoor("door");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isLocked = createBoolean("isLocked");

    public QDoor(String variable) {
        super(Door.class, forVariable(variable));
    }

    public QDoor(Path<? extends Door> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDoor(PathMetadata metadata) {
        super(Door.class, metadata);
    }

}

