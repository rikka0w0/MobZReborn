package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class GiantToad extends ToadEntity {
	public GiantToad(EntityType<? extends ToadEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public boolean canToadTarget(LivingEntity entity) {
		if (entity instanceof Player) {
			Player player = (Player) entity;
			return !player.isCreative();
		}

		return !(entity instanceof GiantToad);
	}

	@Override
	public int getSpotRange() {
		return 10;
	}

	@Override
	protected float getTongueSpeed() {
		return 1F;
	}

	@Override
	protected float getTongueSpeed2() {
		return 0.8F;
	}
}
