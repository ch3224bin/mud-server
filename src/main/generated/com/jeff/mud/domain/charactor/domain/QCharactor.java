package com.jeff.mud.domain.charactor.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCharactor is a Querydsl query type for Charactor
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCharactor extends EntityPathBase<Charactor> {

    private static final long serialVersionUID = -196529898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCharactor charactor = new QCharactor("charactor");

    public final QCharactorBag charactorBag;

    public final QEquipment equipment;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.jeff.mud.domain.room.domain.QRoom room;

    public final ListPath<com.jeff.mud.domain.skill.domain.Skill, com.jeff.mud.domain.skill.domain.QSkill> skills = this.<com.jeff.mud.domain.skill.domain.Skill, com.jeff.mud.domain.skill.domain.QSkill>createList("skills", com.jeff.mud.domain.skill.domain.Skill.class, com.jeff.mud.domain.skill.domain.QSkill.class, PathInits.DIRECT2);

    public final EnumPath<com.jeff.mud.state.CharactorState> state = createEnum("state", com.jeff.mud.state.CharactorState.class);

    public final ListPath<com.jeff.mud.domain.stat.domain.Stat, com.jeff.mud.domain.stat.domain.QStat> stats = this.<com.jeff.mud.domain.stat.domain.Stat, com.jeff.mud.domain.stat.domain.QStat>createList("stats", com.jeff.mud.domain.stat.domain.Stat.class, com.jeff.mud.domain.stat.domain.QStat.class, PathInits.DIRECT2);

    public final QStatus status;

    public QCharactor(String variable) {
        this(Charactor.class, forVariable(variable), INITS);
    }

    public QCharactor(Path<? extends Charactor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCharactor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCharactor(PathMetadata metadata, PathInits inits) {
        this(Charactor.class, metadata, inits);
    }

    public QCharactor(Class<? extends Charactor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactorBag = inits.isInitialized("charactorBag") ? new QCharactorBag(forProperty("charactorBag"), inits.get("charactorBag")) : null;
        this.equipment = inits.isInitialized("equipment") ? new QEquipment(forProperty("equipment"), inits.get("equipment")) : null;
        this.room = inits.isInitialized("room") ? new com.jeff.mud.domain.room.domain.QRoom(forProperty("room")) : null;
        this.status = inits.isInitialized("status") ? new QStatus(forProperty("status"), inits.get("status")) : null;
    }

}

