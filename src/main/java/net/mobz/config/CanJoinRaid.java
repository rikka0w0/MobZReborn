package net.mobz.config;

import net.minecraft.world.entity.raid.Raider;

public enum CanJoinRaid {
	NEVER,
	ALWAYS,
	SURFACE_ONLY,
	;

	public boolean check(Raider raider) {
		switch (this) {
		case NEVER:
			break;
		case ALWAYS:
			return true;
		case SURFACE_ONLY:
			return raider.level().canSeeSky(raider.blockPosition());
		default:
			break;
		}
		return false;
	}

	public interface Provider {
		CanJoinRaid canJoinRaid();

		// A helper function
		default boolean canJoinRaid(Raider raider) {
			return this.canJoinRaid().check(raider);
		}
	}
}
