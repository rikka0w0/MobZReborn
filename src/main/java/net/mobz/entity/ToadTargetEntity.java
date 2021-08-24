package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.mobz.MathUtils;
import net.mobz.init.MobZSounds;

public class ToadTargetEntity extends PathfinderMob {
	private ToadEntity eatenBy = null;

	protected ToadTargetEntity(EntityType<? extends PathfinderMob> entityType,
			Level world) {
		super(entityType, world);
	}

	public void setEatenBy(ToadEntity toad) {
		this.eatenBy = toad;
	}

	public boolean isBeingEaten() {
		return eatenBy != null;
	}

	@Override
	public void tick() {
		if (!isBeingEaten()) {
			super.tick();
		} else {
			if (eatenBy == null || eatenBy.isDeadOrDying()
					|| eatenBy.isRemoved()) {
				setEatenBy(null);
				return;
			}

			if (eatenBy.isTongueReady()) {
				double xx = MathUtils.approachValue(position().x,
						eatenBy.getX(), 0.7D);
				double yy = MathUtils.approachValue(position().y,
						eatenBy.getY() + 0.2F, 0.5D);
				double zz = MathUtils.approachValue(position().z,
						eatenBy.getZ(), 0.7D);
				absMoveTo(xx, yy, zz, getYRot(), getXRot());
				setPosRaw(xx, yy, zz);
				setDeltaMovement(0, 0, 0);

				if (distanceTo(eatenBy) <= 0.2F) {
					eatenBy.playSound(MobZSounds.TOAD_SWALLOW, 1F,
							1F + ((float) random.nextGaussian() / 5F));
					this.discard();
				}
			}
		}
	}

	public boolean canBeLeashed(Player player) {
		return false;
	}
}
