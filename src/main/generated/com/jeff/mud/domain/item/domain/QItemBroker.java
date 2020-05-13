package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemBroker is a Querydsl query type for ItemBroker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QItemBroker extends EntityPathBase<ItemBroker> {

    private static final long serialVersionUID = 1072909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemBroker itemBroker = new QItemBroker("itemBroker");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public QItemBroker(String variable) {
        this(ItemBroker.class, forVariable(variable), INITS);
    }

    public QItemBroker(Path<? extends ItemBroker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemBroker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemBroker(PathMetadata metadata, PathInits inits) {
        this(ItemBroker.class, metadata, inits);
    }

    public QItemBroker(Class<? extends ItemBroker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item")) : null;
    }

}

