package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKey is a Querydsl query type for Key
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKey extends EntityPathBase<Key> {

    private static final long serialVersionUID = 1530256190L;

    public static final QKey key = new QKey("key1");

    public final QItem _super = new QItem(this);

    //inherited
    public final StringPath description = _super.description;

    public final ListPath<com.jeff.mud.domain.room.domain.Door, com.jeff.mud.domain.room.domain.QDoor> doors = this.<com.jeff.mud.domain.room.domain.Door, com.jeff.mud.domain.room.domain.QDoor>createList("doors", com.jeff.mud.domain.room.domain.Door.class, com.jeff.mud.domain.room.domain.QDoor.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath isGetable = _super.isGetable;

    //inherited
    public final StringPath name = _super.name;

    public QKey(String variable) {
        super(Key.class, forVariable(variable));
    }

    public QKey(Path<? extends Key> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKey(PathMetadata metadata) {
        super(Key.class, metadata);
    }

}

