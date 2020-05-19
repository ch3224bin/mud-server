package com.jeff.mud.domain.charactor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStatus is a Querydsl query type for Status
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStatus extends EntityPathBase<Status> {

    private static final long serialVersionUID = -1834979685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStatus status = new QStatus("status");

    public final QCharactor charactor;

    public final NumberPath<Integer> hp = createNumber("hp", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> luck = createNumber("luck", Integer.class);

    public final NumberPath<Integer> moveRate = createNumber("moveRate", Integer.class);

    public final NumberPath<Integer> mp = createNumber("mp", Integer.class);

    public QStatus(String variable) {
        this(Status.class, forVariable(variable), INITS);
    }

    public QStatus(Path<? extends Status> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStatus(PathMetadata metadata, PathInits inits) {
        this(Status.class, metadata, inits);
    }

    public QStatus(Class<? extends Status> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactor = inits.isInitialized("charactor") ? new QCharactor(forProperty("charactor"), inits.get("charactor")) : null;
    }

}

