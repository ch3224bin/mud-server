package com.jeff.mud.domain.item.constants;

import com.jeff.mud.domain.item.domain.Weapon;

public enum Weapons implements WeaponCreator {
	fist {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, 1, 3);
		}
	},
	knife {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, 1, 4);
		}
	},
	gun {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, 1, 6);
		}
	},
	;
}
