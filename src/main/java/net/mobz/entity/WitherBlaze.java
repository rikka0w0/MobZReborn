package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.init.MobZSounds;

public class WitherBlaze extends BlazeLike {
	public WitherBlaze(EntityType<? extends WitherBlaze> entityType_1, Level world_1) {
		super(entityType_1, world_1);
		this.setPathfindingMalus(PathType.LAVA, 8.0F);
		this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
		this.xpReward = 14;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.wither_blaze.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.23D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.wither_blaze.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 48.0D);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.wither_blaze.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);

	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setAlertOthers(Piglin.class));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers(NetherSkeleton.class));
		this.targetSelector.addGoal(4, (new HurtByTargetGoal(this)).setAlertOthers(Skeleton.class));
		this.targetSelector.addGoal(5, (new HurtByTargetGoal(this)).setAlertOthers(WitherSkeleton.class));
		this.targetSelector.addGoal(6, (new HurtByTargetGoal(this)).setAlertOthers(NetherWolf.class));
		this.targetSelector.addGoal(7, (new HurtByTargetGoal(this)).setAlertOthers(WitherBlaze.class));
		this.targetSelector.addGoal(8, (new HurtByTargetGoal(this)).setAlertOthers(LavaGolem.class));
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (this.level().isClientSide) {
			if (this.random.nextInt(24) == 0 && !this.isSilent()) {
				this.level().playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D,
						MobZSounds.NOTHINGEVENT.get(), this.getSoundSource(), 1.0F + this.random.nextFloat(),
						this.random.nextFloat() * 0.7F + 0.3F, false);
			}

			for (int int_1 = 0; int_1 < 2; ++int_1) {
				this.level().addParticle(ParticleTypes.FALLING_LAVA, this.getRandomX(0.5D), this.getRandomY(),
						this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	protected void adjustHeight() {
		--this.nextHeightOffsetChangeTick;
		if (this.nextHeightOffsetChangeTick <= 0) {
			this.nextHeightOffsetChangeTick = 100;
			this.allowedHeightOffset = 0.5F + (float) this.random.nextGaussian() * 3.0F;
		}
	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		if (this.isInWater()) {
			this.hurtServer(serverLevel, this.damageSources().drown(), 1.0F);
		}

		super.customServerAiStep(serverLevel);
	}

	@Override
	public boolean causeFallDamage(double float_1, float float_2, DamageSource source) {
		return false;
	}
}
