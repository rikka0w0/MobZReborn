package net.mobz.entity.attack;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class FrostballEntity extends AbstractFireballEntity {
	public FrostballEntity(EntityType<? extends FireballEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}

	public FrostballEntity(World world, LivingEntity livingEntity, double double_1, double double_2,
			double double_3) {
		super(EntityType.SMALL_FIREBALL, livingEntity, double_1, double_2, double_3, world);
	}

	public FrostballEntity(World world, double double_1, double double_2, double double_3, double double_4,
			double double_5, double double_6) {
		super(EntityType.SMALL_FIREBALL, double_1, double_2, double_3, double_4, double_5, double_6, world);
	}

	@Override
	protected void onHitEntity(EntityRayTraceResult entityHitResult) {
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
	protected void onHitBlock(BlockRayTraceResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		if (!this.level.isClientSide) {
			Entity entity = this.getOwner();
			if (entity == null || !(entity instanceof MobEntity)
					|| this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
				if (this.level.isEmptyBlock(blockPos)) {
					// this.world.setBlockState(blockPos, SnowBlock.def);
				}
			}

		}
	}

	@Override
	protected void onHit(RayTraceResult hitResult) {
		super.onHit(hitResult);
		if (!this.level.isClientSide) {
			this.remove();
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
