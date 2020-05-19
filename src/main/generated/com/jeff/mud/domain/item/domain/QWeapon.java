package com.jeff.mud.domain.item.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWeapon is a Querydsl query type for Weapon
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWeapon extends EntityPathBase<Weapon> {

    private static final long serialVersionUID = 1422222557L;

    public static final QWeapon weapon = new QWeapon("weapon");

    public final QItem _super = new QItem(this);

    public final NumberPath<Integer> accuracy = createNumber("accuracy", Integer.class);

    public final NumberPath<Integer> bonus = createNumber("bonus", Integer.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final BooleanPath critical = createBoolean("critical");

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final EnumPath<com.jeff.mud.domain.item.constants.ItemGrade> grade = _super.grade;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath isGetable = _super.isGetable;

    //inherited
    public final StringPath name = _super.name;

    public final NumberPath<Integer> sided = createNumber("sided", Integer.class);

    public final EnumPath<com.jeff.mud.domain.item.constants.Weapons> type = createEnum("type", com.jeff.mud.domain.item.constants.Weapons.class);

    public QWeapon(String variable) {
        super(Weapon.class, forVariable(variable));
    }

    public QWeapon(Path<? extends Weapon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWeapon(PathMetadata metadata) {
        super(Weapon.class, metadata);
    }

}

