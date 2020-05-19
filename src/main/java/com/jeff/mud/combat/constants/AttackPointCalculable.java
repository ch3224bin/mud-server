package com.jeff.mud.combat.constants;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.item.domain.Weapon;

public interface AttackPointCalculable {
	int getDamege(Charactor attacker, Weapon weapon);
}
