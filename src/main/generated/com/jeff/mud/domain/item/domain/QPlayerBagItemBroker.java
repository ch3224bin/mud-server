package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayerBagItemBroker is a Querydsl query type for PlayerBagItemBroker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayerBagItemBroker extends EntityPathBase<PlayerBagItemBroker> {

    private static final long serialVersionUID = -956982158L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayerBagItemBroker playerBagItemBroker = new QPlayerBagItemBroker("playerBagItemBroker");

    public final QItemBroker _super;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final QItem item;

    public final com.jeff.mud.domain.player.domain.QPlayerBag playerBag;

    public QPlayerBagItemBroker(String variable) {
        this(PlayerBagItemBroker.class, forVariable(variable), INITS);
    }

    public QPlayerBagItemBroker(Path<? extends PlayerBagItemBroker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayerBagItemBroker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayerBagItemBroker(PathMetadata metadata, PathInits inits) {
        this(PlayerBagItemBroker.class, metadata, inits);
    }

    public QPlayerBagItemBroker(Class<? extends PlayerBagItemBroker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItemBroker(type, metadata, inits);
        this.id = _super.id;
        this.item = _super.item;
        this.playerBag = inits.isInitialized("playerBag") ? new com.jeff.mud.domain.player.domain.QPlayerBag(forProperty("playerBag"), inits.get("playerBag")) : null;
    }

}

