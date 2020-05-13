package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContainerItemBroker is a Querydsl query type for ContainerItemBroker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContainerItemBroker extends EntityPathBase<ContainerItemBroker> {

    private static final long serialVersionUID = 2125520652L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContainerItemBroker containerItemBroker = new QContainerItemBroker("containerItemBroker");

    public final QItemBroker _super;

    public final QContainer container;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final QItem item;

    public QContainerItemBroker(String variable) {
        this(ContainerItemBroker.class, forVariable(variable), INITS);
    }

    public QContainerItemBroker(Path<? extends ContainerItemBroker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContainerItemBroker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContainerItemBroker(PathMetadata metadata, PathInits inits) {
        this(ContainerItemBroker.class, metadata, inits);
    }

    public QContainerItemBroker(Class<? extends ContainerItemBroker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItemBroker(type, metadata, inits);
        this.container = inits.isInitialized("container") ? new QContainer(forProperty("container"), inits.get("container")) : null;
        this.id = _super.id;
        this.item = _super.item;
    }

}

