package com.jeff.mud.domain.charactor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlayer is a Querydsl query type for Player
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPlayer extends EntityPathBase<Player> {

    private static final long serialVersionUID = -1928250998L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlayer player = new QPlayer("player");

    public final QCharactor _super;

    public final com.jeff.mud.global.account.domain.QAccount account;

    // inherited
    public final QCharactorBag charactorBag;

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isOnline = createBoolean("isOnline");

    //inherited
    public final StringPath name;

    // inherited
    public final com.jeff.mud.domain.room.domain.QRoom room;

    //inherited
    public final EnumPath<com.jeff.mud.state.CharactorState> state;

    public QPlayer(String variable) {
        this(Player.class, forVariable(variable), INITS);
    }

    public QPlayer(Path<? extends Player> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlayer(PathMetadata metadata, PathInits inits) {
        this(Player.class, metadata, inits);
    }

    public QPlayer(Class<? extends Player> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCharactor(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.jeff.mud.global.account.domain.QAccount(forProperty("account")) : null;
        this.charactorBag = _super.charactorBag;
        this.id = _super.id;
        this.name = _super.name;
        this.room = _super.room;
        this.state = _super.state;
    }

}

