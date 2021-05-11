package com.jeff.mud.domain.room.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArea is a Querydsl query type for Area
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArea extends EntityPathBase<Area> {

    private static final long serialVersionUID = 184731990L;

    public static final QArea area = new QArea("area");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<Room, QRoom> rooms = this.<Room, QRoom>createList("rooms", Room.class, QRoom.class, PathInits.DIRECT2);

    public final EnumPath<com.jeff.mud.domain.room.constants.AreaType> type = createEnum("type", com.jeff.mud.domain.room.constants.AreaType.class);

    public QArea(String variable) {
        super(Area.class, forVariable(variable));
    }

    public QArea(Path<? extends Area> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArea(PathMetadata metadata) {
        super(Area.class, metadata);
    }

}

