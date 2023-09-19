package net.mobz.entity.attack;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.mobz.init.MobZEntities;
import net.mobz.init.MobZItems;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

public class FrostballEntity extends Fireball {
	public FrostballEntity(EntityType<? extends FrostballEntity> entityType, Level world) {
		super(entityType, world);
	}

    public FrostballEntity(Level world, LivingEntity livingEntity, double double_1, double double_2, double double_3) {
        super(MobZEntities.FROSTBALLENTITY.get(), livingEntity, double_1, double_2, double_3, world);
    }

    public FrostballEntity(Level world, double double_1, double double_2, double double_3, double double_4,
            double double_5, double double_6) {
        super(MobZEntities.FROSTBALLENTITY.get(), double_1, double_2, double_3, double_4, double_5, double_6, world);
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(MobZItems.FROZENMEAL.get());
    }

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		if (!this.level().isClientSide) {
			Entity entity = entityHitResult.getEntity();
			if (entity instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) entity;
				livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100));
				entity.hurt(this.damageSources().fireball(this, this.getOwner()), 5.0F);
			}
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		if (!this.level().isClientSide) {
			Entity entity = this.getOwner();
			if (entity == null || !(entity instanceof Mob)
					|| this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
				BlockPos blockPos = blockHitResult.getBlockPos().relative(blockHitResult.getDirection());
				if (this.level().isEmptyBlock(blockPos)) {
					this.level().setBlockAndUpdate(blockPos, Blocks.SNOW.defaultBlockState());
				}
			}
		}
	}

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide) {
			this.discard();
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
