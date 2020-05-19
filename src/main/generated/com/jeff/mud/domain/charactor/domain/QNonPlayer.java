package com.jeff.mud.domain.charactor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNonPlayer is a Querydsl query type for NonPlayer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNonPlayer extends EntityPathBase<NonPlayer> {

    private static final long serialVersionUID = -1599563579L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNonPlayer nonPlayer = new QNonPlayer("nonPlayer");

    public final QCharactor _super;

    // inherited
    public final QCharactorBag charactorBag;

    public final StringPath description = createString("description");

    // inherited
    public final QEquipment equipment;

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isAttackable = createBoolean("isAttackable");

    //inherited
    public final StringPath name;

    // inherited
    public final com.jeff.mud.domain.room.domain.QRoom room;

    //inherited
    public final ListPath<com.jeff.mud.domain.skill.domain.Skill, com.jeff.mud.domain.skill.domain.QSkill> skills;

    //inherited
    public final EnumPath<com.jeff.mud.state.CharactorState> state;

    //inherited
    public final ListPath<com.jeff.mud.domain.stat.domain.Stat, com.jeff.mud.domain.stat.domain.QStat> stats;

    // inherited
    public final QStatus status;

    public QNonPlayer(String variable) {
        this(NonPlayer.class, forVariable(variable), INITS);
    }

    public QNonPlayer(Path<? extends NonPlayer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNonPlayer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNonPlayer(PathMetadata metadata, PathInits inits) {
        this(NonPlayer.class, metadata, inits);
    }

    public QNonPlayer(Class<? extends NonPlayer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QCharactor(type, metadata, inits);
        this.charactorBag = _super.charactorBag;
        this.equipment = _super.equipment;
        this.id = _super.id;
        this.name = _super.name;
        this.room = _super.room;
        this.skills = _super.skills;
        this.state = _super.state;
        this.stats = _super.stats;
        this.status = _super.status;
    }

}

