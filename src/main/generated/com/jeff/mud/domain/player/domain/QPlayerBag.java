package com.jeff.mud.domain.player.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayerBag is a Querydsl query type for PlayerBag
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayerBag extends EntityPathBase<PlayerBag> {

    private static final long serialVersionUID = -1171096200L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayerBag playerBag = new QPlayerBag("playerBag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.jeff.mud.domain.item.domain.PlayerBagItemBroker, com.jeff.mud.domain.item.domain.QPlayerBagItemBroker> itemBrokers = this.<com.jeff.mud.domain.item.domain.PlayerBagItemBroker, com.jeff.mud.domain.item.domain.QPlayerBagItemBroker>createList("itemBrokers", com.jeff.mud.domain.item.domain.PlayerBagItemBroker.class, com.jeff.mud.domain.item.domain.QPlayerBagItemBroker.class, PathInits.DIRECT2);

    public final QPlayer player;

    public QPlayerBag(String variable) {
        this(PlayerBag.class, forVariable(variable), INITS);
    }

    public QPlayerBag(Path<? extends PlayerBag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayerBag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayerBag(PathMetadata metadata, PathInits inits) {
        this(PlayerBag.class, metadata, inits);
    }

    public QPlayerBag(Class<? extends PlayerBag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.player = inits.isInitialized("player") ? new QPlayer(forProperty("player"), inits.get("player")) : null;
    }

}

