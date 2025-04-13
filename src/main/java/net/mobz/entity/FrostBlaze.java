package net.mobz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.Level;

import net.mobz.MobZ;
import net.mobz.entity.attack.FrostballEntity;
import net.mobz.init.MobZSounds;

public class FrostBlaze extends BlazeLike {
	public FrostBlaze(EntityType<? extends FrostBlaze> entityType_1, Level world_1) {
		super(entityType_1, world_1);
		this.setPathfindingMalus(PathType.WATER, 8.0F);
		this.xpReward = 10;
	}

	public static AttributeSupplier.Builder createMobzAttributes() {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, MobZ.configs.frost_blaze.life * MobZ.configs.life_multiplier)
				.add(Attributes.MOVEMENT_SPEED, 0.23D)
				.add(Attributes.ATTACK_DAMAGE, MobZ.configs.frost_blaze.attack * MobZ.configs.damage_multiplier)
				.add(Attributes.FOLLOW_RANGE, 48.0D);
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader view) {
		return MobZ.configs.frost_blaze.spawn && MobSpawnHelper.checkSpawnObstruction(this, view);

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SNOW_GOLEM_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return SoundEvents.SNOW_GOLEM_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SNOW_GOLEM_DEATH;
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
				this.level().addParticle(ParticleTypes.ITEM_SNOWBALL, this.getRandomX(0.5D), this.getRandomY(),
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
	public boolean causeFallDamage(double float_1, float float_2, DamageSource source) {
		return false;
	}

	@Override
	public boolean displayFireAnimation() {
		return false;
	}

	@Override
	protected void shootFireball(double xOffset, double yOffset, double zOffset, double distanceSqr) {
		double d4 = Math.sqrt(Math.sqrt(distanceSqr)) * 0.5;
		for (int fireCount = 0; fireCount < 1; ++fireCount) {
			Vec3 displacementVector = new Vec3(xOffset + this.getRandom().nextGaussian() * d4, yOffset,
					zOffset + this.getRandom().nextGaussian() * d4);
			FrostballEntity bullet = new FrostballEntity(this.level(), this, displacementVector.normalize());
			bullet.setPos(bullet.getX(), this.getY(0.5D) + 0.5D, bullet.getZ());
			this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level().addFreshEntity(bullet);
		}
	}
}
