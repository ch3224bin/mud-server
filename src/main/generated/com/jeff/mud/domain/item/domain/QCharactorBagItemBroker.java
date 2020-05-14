package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharactorBagItemBroker is a Querydsl query type for CharactorBagItemBroker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCharactorBagItemBroker extends EntityPathBase<CharactorBagItemBroker> {

    private static final long serialVersionUID = 294566294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCharactorBagItemBroker charactorBagItemBroker = new QCharactorBagItemBroker("charactorBagItemBroker");

    public final QItemBroker _super;

    public final com.jeff.mud.domain.charactor.domain.QCharactorBag charactorBag;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final QItem item;

    public QCharactorBagItemBroker(String variable) {
        this(CharactorBagItemBroker.class, forVariable(variable), INITS);
    }

    public QCharactorBagItemBroker(Path<? extends CharactorBagItemBroker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCharactorBagItemBroker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCharactorBagItemBroker(PathMetadata metadata, PathInits inits) {
        this(CharactorBagItemBroker.class, metadata, inits);
    }

    public QCharactorBagItemBroker(Class<? extends CharactorBagItemBroker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItemBroker(type, metadata, inits);
        this.charactorBag = inits.isInitialized("charactorBag") ? new com.jeff.mud.domain.charactor.domain.QCharactorBag(forProperty("charactorBag"), inits.get("charactorBag")) : null;
        this.id = _super.id;
        this.item = _super.item;
    }

}

