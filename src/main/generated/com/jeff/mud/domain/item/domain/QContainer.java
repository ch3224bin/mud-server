package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContainer is a Querydsl query type for Container
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContainer extends EntityPathBase<Container> {

    private static final long serialVersionUID = 371598880L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContainer container = new QContainer("container");

    public final QItem _super = new QItem(this);

    //inherited
    public final StringPath description = _super.description;

    public final com.jeff.mud.domain.room.domain.QDoor door;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath isGetable = _super.isGetable;

    //inherited
    public final StringPath name = _super.name;

    public QContainer(String variable) {
        this(Container.class, forVariable(variable), INITS);
    }

    public QContainer(Path<? extends Container> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContainer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContainer(PathMetadata metadata, PathInits inits) {
        this(Container.class, metadata, inits);
    }

    public QContainer(Class<? extends Container> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.door = inits.isInitialized("door") ? new com.jeff.mud.domain.room.domain.QDoor(forProperty("door")) : null;
    }

}

