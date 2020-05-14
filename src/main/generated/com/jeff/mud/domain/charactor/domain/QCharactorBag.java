package com.jeff.mud.domain.charactor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharactorBag is a Querydsl query type for CharactorBag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCharactorBag extends EntityPathBase<CharactorBag> {

    private static final long serialVersionUID = -781700334L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCharactorBag charactorBag = new QCharactorBag("charactorBag");

    public final QCharactor charactor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.jeff.mud.domain.item.domain.CharactorBagItemBroker, com.jeff.mud.domain.item.domain.QCharactorBagItemBroker> itemBrokers = this.<com.jeff.mud.domain.item.domain.CharactorBagItemBroker, com.jeff.mud.domain.item.domain.QCharactorBagItemBroker>createList("itemBrokers", com.jeff.mud.domain.item.domain.CharactorBagItemBroker.class, com.jeff.mud.domain.item.domain.QCharactorBagItemBroker.class, PathInits.DIRECT2);

    public QCharactorBag(String variable) {
        this(CharactorBag.class, forVariable(variable), INITS);
    }

    public QCharactorBag(Path<? extends CharactorBag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCharactorBag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCharactorBag(PathMetadata metadata, PathInits inits) {
        this(CharactorBag.class, metadata, inits);
    }

    public QCharactorBag(Class<? extends CharactorBag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactor = inits.isInitialized("charactor") ? new QCharactor(forProperty("charactor"), inits.get("charactor")) : null;
    }

}

