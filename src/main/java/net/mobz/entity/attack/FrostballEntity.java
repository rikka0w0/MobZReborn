package net.mobz.entity.attack;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class FrostballEntity extends Fireball {
	public FrostballEntity(EntityType<? extends LargeFireball> entityType_1, Level world_1) {
		super(entityType_1, world_1);
	}

	public FrostballEntity(Level world, LivingEntity livingEntity, double double_1, double double_2,
			double double_3) {
		// TODO: Register FrostballEntity
		super(EntityType.SMALL_FIREBALL, livingEntity, double_1, double_2, double_3, world);
	}

	public FrostballEntity(Level world, double double_1, double double_2, double double_3, double double_4,
			double double_5, double double_6) {
		super(EntityType.SMALL_FIREBALL, double_1, double_2, double_3, double_4, double_5, double_6, world);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		if (!this.level.isClientSide) {
			Entity entity = entityHitResult.getEntity();
			if (!entity.fireImmune()) {
				Entity entity2 = this.getOwner();
				int i = entity.getRemainingFireTicks();
				entity.setSecondsOnFire(5);
				boolean bl = entity.hurt(DamageSource.fireball(this, entity2), 5.0F);
				if (!bl) {
					entity.setRemainingFireTicks(i);
				} else if (entity2 instanceof LivingEntity) {
					this.doEnchantDamageEffects((LivingEntity) entity2, entity);
				}
			}

		}
	}

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		if (!this.level.isClientSide) {
			Entity entity = this.getOwner();
			if (entity == null || !(entity instanceof Mob)
					|| this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
				if (this.level.isEmptyBlock(blockPos)) {
					// this.world.setBlockState(blockPos, SnowBlock.def);
				}
			}

		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level.isClientSide) {
			// TODO: check onHit, this line may not be necessary
			this.remove(Entity.RemovalReason.KILLED);
		}
	}

	@Override
	public boolean isPickable() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource damageSource_1, float float_1) {
		return false;
	}
}
