package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomItemBroker is a Querydsl query type for RoomItemBroker
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRoomItemBroker extends EntityPathBase<RoomItemBroker> {

    private static final long serialVersionUID = 1051958728L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomItemBroker roomItemBroker = new QRoomItemBroker("roomItemBroker");

    public final QItemBroker _super;

    //inherited
    public final NumberPath<Long> id;

    // inherited
    public final QItem item;

    public final com.jeff.mud.domain.room.domain.QRoom room;

    public QRoomItemBroker(String variable) {
        this(RoomItemBroker.class, forVariable(variable), INITS);
    }

    public QRoomItemBroker(Path<? extends RoomItemBroker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomItemBroker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomItemBroker(PathMetadata metadata, PathInits inits) {
        this(RoomItemBroker.class, metadata, inits);
    }

    public QRoomItemBroker(Class<? extends RoomItemBroker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QItemBroker(type, metadata, inits);
        this.id = _super.id;
        this.item = _super.item;
        this.room = inits.isInitialized("room") ? new com.jeff.mud.domain.room.domain.QRoom(forProperty("room")) : null;
    }

}

