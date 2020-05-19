package com.jeff.mud.domain.skill.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkill is a Querydsl query type for Skill
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSkill extends EntityPathBase<Skill> {

    private static final long serialVersionUID = 1319966422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSkill skill = new QSkill("skill");

    public final com.jeff.mud.domain.charactor.domain.QCharactor charactor;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final EnumPath<com.jeff.mud.domain.skill.constants.Skills> type = createEnum("type", com.jeff.mud.domain.skill.constants.Skills.class);

    public QSkill(String variable) {
        this(Skill.class, forVariable(variable), INITS);
    }

    public QSkill(Path<? extends Skill> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSkill(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSkill(PathMetadata metadata, PathInits inits) {
        this(Skill.class, metadata, inits);
    }

    public QSkill(Class<? extends Skill> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.charactor = inits.isInitialized("charactor") ? new com.jeff.mud.domain.charactor.domain.QCharactor(forProperty("charactor"), inits.get("charactor")) : null;
    }

}

