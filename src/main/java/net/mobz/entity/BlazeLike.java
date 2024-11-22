package net.mobz.entity;

import java.util.EnumSet;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlazeLike extends Monster {
	protected float allowedHeightOffset = 0.5F;
	protected int nextHeightOffsetChangeTick;
	private static final EntityDataAccessor<Byte> BLAZE_LIKE_FLAGS =
			SynchedEntityData.defineId(BlazeLike.class, EntityDataSerializers.BYTE);

	public BlazeLike(EntityType<? extends BlazeLike> entityType, Level level) {
		super(entityType, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new BlazeLikeAttackGoal(this));

		// Add all vanilla goals except for the attack goal
		this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0, 0.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(BLAZE_LIKE_FLAGS, (byte) 0);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BLAZE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return SoundEvents.BLAZE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLAZE_DEATH;
	}

	@Override
	public float getLightLevelDependentMagicValue() {
		return 1.0F;
	}

	@Override
	public void aiStep() {
		if (!this.onGround() && this.getDeltaMovement().y < 0.0D) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
		}

		super.aiStep();
	}

	@Override
	public boolean isSensitiveToWater() {
		return true;
	}

	// Only runs on server side
	protected void adjustHeight() {
		this.nextHeightOffsetChangeTick--;
		if (this.nextHeightOffsetChangeTick <= 0) {
			this.nextHeightOffsetChangeTick = 100;
			this.allowedHeightOffset = (float) this.random.triangle(0.5, 6.891);
		}
	}

	@Override
	protected void customServerAiStep(ServerLevel serverLevel) {
		this.adjustHeight();

		LivingEntity victim = this.getTarget();
		if (victim != null && victim.getEyeY() > this.getEyeY() + this.allowedHeightOffset && this.canAttack(victim)) {
			Vec3 delta = this.getDeltaMovement();
			this.setDeltaMovement(this.getDeltaMovement().add(0.0, (0.3 - delta.y) * 0.3, 0.0));
			this.hasImpulse = true;
		}

		super.customServerAiStep(serverLevel);
	}

	@Override
	public boolean isOnFire() {
		return this.isCharged();
	}

	protected boolean isCharged() {
		return (this.entityData.get(BLAZE_LIKE_FLAGS) & 1) != 0;
	}

	protected void setChargedState(boolean newValue) {
		byte flags = this.entityData.get(BLAZE_LIKE_FLAGS);
		if (newValue) {
			flags = (byte) (flags | 1);
		} else {
			flags &= -2;
		}

		this.entityData.set(BLAZE_LIKE_FLAGS, flags);
	}

	protected void shootFireball(double xOffset, double yOffset, double zOffset, double distanceSqr) {
		double d4 = Math.sqrt(Math.sqrt(distanceSqr)) * 0.5;
		for (int i = 0; i < 1; i++) {
			Vec3 displacement = new Vec3(this.getRandom().triangle(xOffset, 2.297 * d4), yOffset,
					this.getRandom().triangle(zOffset, 2.297 * d4));
			SmallFireball bullet = new SmallFireball(this.level(), this, displacement.normalize());
			bullet.setPos(bullet.getX(), this.getY(0.5) + 0.5, bullet.getZ());
			this.level().addFreshEntity(bullet);
		}
	}

	protected static class BlazeLikeAttackGoal extends Goal {
		private final BlazeLike owner;
		private int attackStep;
		private int attackTime;
		private int lastSeen;

		public BlazeLikeAttackGoal(BlazeLike blazeLike) {
			this.owner = blazeLike;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity victim = this.owner.getTarget();
			return victim != null && victim.isAlive() && this.owner.canAttack(victim);
		}

		@Override
		public void start() {
			this.attackStep = 0;
		}

		@Override
		public void stop() {
			this.owner.setChargedState(false);
			this.lastSeen = 0;
		}

		@Override
		public boolean requiresUpdateEveryTick() {
			return true;
		}

		@Override
		public void tick() {
			this.attackTime--;
			LivingEntity victim = this.owner.getTarget();
			if (victim != null) {
				boolean canSee = this.owner.getSensing().hasLineOfSight(victim);
				if (canSee) {
					this.lastSeen = 0;
				} else {
					this.lastSeen++;
				}

				double distanceSqr = this.owner.distanceToSqr(victim);
				if (distanceSqr < 4.0) {
					if (!canSee) {
						return;
					}

					if (this.attackTime <= 0) {
						this.attackTime = 20;
						this.owner.doHurtTarget(getServerLevel(this.owner), victim);
					}

					this.owner.getMoveControl().setWantedPosition(victim.getX(), victim.getY(), victim.getZ(), 1.0);
				} else if (distanceSqr < this.getFollowDistance() * this.getFollowDistance() && canSee) {
					double xOffset = victim.getX() - this.owner.getX();
					double yOffset = victim.getY(0.5) - this.owner.getY(0.5);
					double zOffset = victim.getZ() - this.owner.getZ();
					if (this.attackTime <= 0) {
						this.attackStep++;
						if (this.attackStep == 1) {
							this.attackTime = 60;
							this.owner.setChargedState(true);
						} else if (this.attackStep <= 4) {
							this.attackTime = 6;
						} else {
							this.attackTime = 100;
							this.attackStep = 0;
							this.owner.setChargedState(false);
						}

						if (this.attackStep > 1) {
							if (!this.owner.isSilent()) {
								this.owner.level().levelEvent(null, 1018, this.owner.blockPosition(), 0);
							}

							this.owner.shootFireball(xOffset, yOffset, zOffset, distanceSqr);
						}
					}

					this.owner.getLookControl().setLookAt(victim, 10.0F, 10.0F);
				} else if (this.lastSeen < 5) {
					this.owner.getMoveControl().setWantedPosition(victim.getX(), victim.getY(), victim.getZ(), 1.0);
				}

				super.tick();
			}
		}

		private double getFollowDistance() {
			return this.owner.getAttributeValue(Attributes.FOLLOW_RANGE);
		}
	}
}
